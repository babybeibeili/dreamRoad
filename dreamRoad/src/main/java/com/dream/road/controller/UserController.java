package com.dream.road.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2020-06-09
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
    return ResultGenerator.genSuccessResult(userService.list());
    }


    /**
    * 根据id查询
    */
    @ApiOperation(value = "根据id查询数据")
    @RequestMapping(value = "/getById")
    public Result<User> getById(@RequestParam("id") String id){
    return ResultGenerator.genSuccessResult(userService.getById(id));
    }

    /**
    * 新增
    */
    @ApiOperation(value = "新增数据")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<User> add(@RequestBody User user){
    return null;
    }

    /**
    * 删除
    */
    @ApiOperation(value = "删除数据")
    @RequestMapping(value = "/del")
    public Result<String> delete(@RequestParam("ids") List<String> ids){
    return null;
    }

    /**
    * 修改
    */
    @ApiOperation(value = "更新数据")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<User> update(@RequestBody User user){
    return null;
    }

    }
