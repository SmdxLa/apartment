package top.crcbest.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import top.crcbest.model.entity.*;
import top.crcbest.model.enums.ItemType;
import top.crcbest.web.admin.mapper.ApartmentInfoMapper;
import top.crcbest.web.admin.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.crcbest.web.admin.vo.apartment.ApartmentDetailVo;
import top.crcbest.web.admin.vo.apartment.ApartmentItemVo;
import top.crcbest.web.admin.vo.apartment.ApartmentQueryVo;
import top.crcbest.web.admin.vo.apartment.ApartmentSubmitVo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liubo
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
        implements ApartmentInfoService {

    @Autowired
    ApartmentFacilityService apartmentFacilityService;

    @Autowired
    ApartmentFeeValueService apartmentFeeValueService;

    @Autowired
    ApartmentLabelService apartmentLabelService;

    @Autowired
    GraphInfoService graphInfoService;

    @Autowired
    ApartmentInfoMapper apartmentInfoMapper;
    @Override
    public void saveOrUpdateApartment(ApartmentSubmitVo apartmentSubmitVo) {
        //1.获取公寓id
        Long theApartmentId = apartmentSubmitVo.getId();

        //2.判断是更新还是保存，更新需要先删除原有的关系信息
        //如果ID不为空，说明是更新操作，需要先删除原有的关系信息
        Boolean flag = (theApartmentId!= null && theApartmentId != 0);

        if(flag){
            //只要是公寓的id，直接删除
            apartmentFacilityService.remove(new LambdaQueryWrapper<ApartmentFacility>().eq(ApartmentFacility::getApartmentId, theApartmentId));
            apartmentFeeValueService.remove(new LambdaQueryWrapper<ApartmentFeeValue>().eq(ApartmentFeeValue::getApartmentId, theApartmentId));
            apartmentLabelService.remove(new LambdaQueryWrapper<ApartmentLabel>().eq(ApartmentLabel::getApartmentId, theApartmentId));
            //因为图片可能是房间的，所以要加上itemType
            graphInfoService.remove(new LambdaQueryWrapper<GraphInfo>().eq(GraphInfo::getItemId, theApartmentId).eq(GraphInfo::getItemType, ItemType.APARTMENT));
        }

        //3.保存公寓信息
        super.saveOrUpdate(apartmentSubmitVo);//保存或更新公寓信息

        //如果是保存，在saveOrUpdate后才能获取到id值，所以需要再次获取
        Long apartmentId = apartmentSubmitVo.getId();

        //保存和其他表的关系信息
        //3.1配套
        apartmentSubmitVo.getFacilityInfoIds()
                .stream()//变成流
                .map(facilityInfoId -> new ApartmentFacility(apartmentId, facilityInfoId))//将facilityInfoId转换为ApartmentFacility，即将公寓id和设施id关联，一个公寓对应多个设施
                .forEach(apartmentFacilityService::saveOrUpdate);//保存每一个新生成的关系到数据库

        //3.2杂费
        apartmentSubmitVo.getFeeValueIds()
                .stream()
                .map(feeValueId -> new ApartmentFeeValue(apartmentId, feeValueId))
                .forEach(apartmentFeeValueService::saveOrUpdate);

        //3.3标签
        apartmentSubmitVo.getLabelIds()
                .stream()
                .map(labelId -> new ApartmentLabel(apartmentId, labelId))
                .forEach(apartmentLabelService::saveOrUpdate);

        //3.4图片（1对多），前端只传了图片id和图片url
        graphInfoService.saveOrUpdateBatch(//批量保存
                apartmentSubmitVo.getGraphVoList()
                        .stream()
                        .map(graphVo -> {
                            GraphInfo graphInfo = new GraphInfo();
                            BeanUtils.copyProperties(graphVo, graphInfo);
                            graphInfo.setItemType(ItemType.APARTMENT);
                            graphInfo.setItemId(apartmentId);
                            return graphInfo;
                        })
                        .collect(Collectors.toList())//将流转换为list
        );

//        Builder注解版
//        graphInfoService.saveOrUpdateBatch(//批量保存
//                apartmentSubmitVo.getGraphVoList()
//                        .stream()
//                        .map(graphVo ->
//                             GraphInfo.builder()
//                                    .name(graphVo.getName())
//                                    .url(graphVo.getUrl())
//                                    .itemType(ItemType.APARTMENT)
//                                    .itemId(apartmentId)
//                                    .build()
//                        )
//                        .collect(Collectors.toList())//将流转换为list
//        );


//        apartmentSubmitVo.getGraphVoList()
//                .forEach(graphVo ->{
//                            GraphInfo graphInfo = new GraphInfo();
//                            BeanUtils.copyProperties(graphVo, graphInfo);
//                            graphInfo.setItemType(ItemType.APARTMENT);
//                            graphInfo.setItemId(apartmentId);
//                        });

    }

    @Override
    public void pageApartmentItemByQuery(IPage<ApartmentItemVo> page, ApartmentQueryVo queryVo) {
       apartmentInfoMapper.pageApartmentItemByQuery(page, queryVo);
    }

    @Override
    public ApartmentDetailVo getApartmentDetailById(Long id) {
        apartmentInfoMapper.getApartmentDetailById(id);
        return null;
    }
}
