package com.yxz.base.mms.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yxz.base.common.exception.BizCodeEnume;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.R;
import com.yxz.base.common.vo.MemberSignUpVo;
import com.yxz.base.mms.Exception.EmailExistedException;
import com.yxz.base.mms.Exception.MobileExistedException;
import com.yxz.base.mms.Exception.UsernameExistedException;
import com.yxz.base.mms.entity.MemberEntity;
import com.yxz.base.mms.feign.CouponFeignService;
import com.yxz.base.mms.service.MemberService;



/**
 * 会员
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-08 09:47:05
 */
@RestController
@RequestMapping("mms/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    CouponFeignService couponFeignService;

    @RequestMapping("/coupons")
    public R test(){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setNickname("张三");

        R membercoupons = couponFeignService.membercoupons();
        return R.ok().put("member",memberEntity).put("coupons",membercoupons.get("coupons"));
    }

    
//    @PostMapping("/login")
//    public R login(MemberLoginVo vo){
//
//
//        MemberRespVo respVo = memberService.login(vo);
//
//        return R.ok(respVo);
//    }



   // @ApiOperation("用户注册")
    @PostMapping("/signup")
    public R signup(@RequestBody MemberSignUpVo vo) {

        try {
            memberService.SignUp(vo);

        } catch (MobileExistedException e){        	
        	R.error(BizCodeEnume.PHONE_EXISTED_EXCEPTION.getCode(), BizCodeEnume.PHONE_EXISTED_EXCEPTION.getMsg());           
        }catch (EmailExistedException e) {
        	R.error(BizCodeEnume.EMAIL_EXISTED_EXCEPTION.getCode(), BizCodeEnume.EMAIL_EXISTED_EXCEPTION.getMsg());
        }catch (UsernameExistedException e) {
	    	R.error(BizCodeEnume.USER_EXISTED_EXCEPTION.getCode(), BizCodeEnume.USER_EXISTED_EXCEPTION.getMsg());
		}

        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list/{sourceType}")
    //@RequiresPermissions("member:member:list")
    public R list(@RequestParam Map<String, Object> params,@PathVariable("sourceType") String sourceType){
//        PageUtils page = memberService.queryPage(params);
        PageUtils page = memberService.queryPageBySourceType(params,sourceType);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:member:info")
    public R info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:member:save")
    public R save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:member:update")
    public R update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok();
    }

    
    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:member:delete")
    public R delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
