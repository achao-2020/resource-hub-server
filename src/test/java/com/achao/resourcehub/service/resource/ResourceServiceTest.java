package com.achao.resourcehub.service.resource;

import com.achao.resourcehub.SpringBaseTest;
import com.achao.resourcehub.controller.resource.param.ResourceQueryParam;
import com.achao.resourcehub.controller.resource.param.ResourceSaveParam;
import com.achao.resourcehub.controller.resource.vo.ResourceQueryVo;
import com.achao.resourcehub.infrastructure.model.enums.ResourceTypeEnum;
import com.achao.resourcehub.infrastructure.model.res.PageQuery;
import com.achao.resourcehub.infrastructure.model.res.PageResult;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

class ResourceServiceTest extends SpringBaseTest {

    @Resource
    private ResourceService resourceService;
    @Test
    void saveResource() {
        ResourceSaveParam saveParam = new ResourceSaveParam();
        saveParam.setName("笑傲江湖");
        saveParam.setType(ResourceTypeEnum.TV.getName());
        saveParam.setDescription("笑傲江湖简介：《笑傲江湖》是中国现代作家金庸创作的一部长篇武侠小说，1967年开始创作并连载于《明报》，1969年完成。这部小说通过叙述华山派大弟子令狐冲的江湖经历，反映了武林各派争霸夺权的历程。作品没有设置时代背景，“类似的情景可以发生在任何朝代”，折射出中国人独特的政治斗争状态，同时也表露出对这种争斗的哀叹，具有一定的政治寓意。小说情节跌宕起伏，波谲云诡，人物形象个性鲜明，生动可感。");
        saveParam.setShareLink("https://www.aliyundrive.com/s/RT27PKErM7x");
        resourceService.saveResource(saveParam);
    }

    @Test
    void updateResource() {
    }

    @Test
    void deleteResource() {
    }

    @Test
    void getResourceById() {
    }

    @Test
    void page() {
        PageQuery<ResourceQueryParam> pageQuery = new PageQuery<>(1, 10);
        ResourceQueryParam resourceQueryParam = new ResourceQueryParam();
        resourceQueryParam.setNeedTag(true);
        pageQuery.setParam(resourceQueryParam);
        PageResult<ResourceQueryVo> page = resourceService.page(pageQuery);
        System.out.println(page);
    }

    @Test
    void pageByTag() {
    }
}