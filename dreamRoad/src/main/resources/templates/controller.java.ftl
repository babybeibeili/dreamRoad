package ${package.Controller};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dream.road.core.Result;
import com.dream.road.core.ResultGenerator;
import java.util.List;
import javax.annotation.Resource;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
    * <p>
    * ${table.comment!} 前端控制器
    * </p>
 *
 * @author ${author}
 * @since ${date}
 * @version v1.0
 */
<#if restControllerStyle>
@Api(tags = {"${table.comment!}"})
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @RequestMapping(value = "/list")
    public Result findListByPage(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,@RequestParam(name = "pageSize", defaultValue = "20") int pageSize){
        Page page = new Page(pageNum, pageSize);
        IPage pageData = ${table.serviceName?uncap_first}.page(page, new QueryWrapper<${entity}>().orderByDesc("id"));
        return ResultGenerator.genSuccessResult(pageData);
    }


    /**
    * 根据id查询
    */
    @ApiOperation(value = "根据id查询数据")
    @RequestMapping(value = "/detail")
    public Result<${entity}> detail(@RequestParam("id") String id){
        return ResultGenerator.genSuccessResult(${table.serviceName?uncap_first}.getById(id));
    }

    /**
    * 新增
    */
    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public Result<${entity}> add(@RequestBody ${entity} ${entity?uncap_first}){
        if (${table.serviceName?uncap_first}.save(${entity?uncap_first})) {
            return ResultGenerator.genSuccessResult(${entity?uncap_first});
        }
        return ResultGenerator.genFailResult("新增数据失败");
    }

    /**
     * 批量删除
     */
    @ApiOperation(value = "批量删除数据")
    @PostMapping(value = "/delMore")
    public Result deleteMore(@RequestParam("ids") List<String> ids) {
        if (${table.serviceName?uncap_first}.removeByIds(ids)) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("批量删除数据失败");
    }

    /**
    * 删除
    */
    @ApiOperation(value = "删除数据")
    @PostMapping("/delete")
    public Result delete(@RequestParam("id") String id){
        if (${table.serviceName?uncap_first}.removeById(id)) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("删除数据失败");
    }

    /**
    * 修改
    */
    @ApiOperation(value = "更新数据")
    @PostMapping("/update")
    public Result update(@RequestBody ${entity} ${entity?uncap_first}){
        UpdateWrapper<${entity}> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",${entity?uncap_first}.getId());
        if (${table.serviceName?uncap_first}.update(${entity?uncap_first},updateWrapper)) {
            return ResultGenerator.genSuccessResult(${entity?uncap_first});
        }
        return ResultGenerator.genFailResult("更新数据失败");
    }

}
</#if>