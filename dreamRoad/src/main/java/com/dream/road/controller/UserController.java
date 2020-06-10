package com.dream.road.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import com.dream.road.service.UserService;
import com.dream.road.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dream.road.core.Result;
import com.dream.road.core.ResultGenerator;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
    * <p>
    * 用户管理 前端控制器
    * </p>
 *
 * @author beibei
 * @since 2020-06-10
 * @version v1.0
 */
@Api(tags = {"用户管理"})
@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private UserService userService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @RequestMapping(value = "/list")
    public Result findListByPage(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,@RequestParam(name = "pageSize", defaultValue = "20") int pageSize){
        Page page = new Page(pageNum, pageSize);
        IPage pageData = userService.page(page, new QueryWrapper<User>().orderByDesc("id"));
        return ResultGenerator.genSuccessResult(pageData);
    }


    /**
    * 根据id查询
    */
    @ApiOperation(value = "根据id查询数据")
    @RequestMapping(value = "/detail")
    public Result<User> detail(@RequestParam("id") String id){
        return ResultGenerator.genSuccessResult(userService.getById(id));
    }

    /**
    * 新增
    */
    @ApiOperation(value = "新增数据")
    @PostMapping("/add")
    public Result<User> add(@RequestBody User user){
        if (userService.save(user)) {
            return ResultGenerator.genSuccessResult(user);
        }
        return ResultGenerator.genFailResult("新增数据失败");
    }

    /**
     * 批量删除
     */
    @ApiOperation(value = "批量删除数据")
    @PostMapping(value = "/delMore")
    public Result deleteMore(@RequestParam("ids") List<String> ids) {
        if (userService.removeByIds(ids)) {
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
        if (userService.removeById(id)) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("删除数据失败");
    }

    /**
    * 修改
    */
    @ApiOperation(value = "更新数据")
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",user.getId());
        if (userService.update(user,updateWrapper)) {
            return ResultGenerator.genSuccessResult(user);
        }
        return ResultGenerator.genFailResult("更新数据失败");
    }

}
