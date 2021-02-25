package cn.tjau.ifarmer.controller;

import cn.tjau.ifarmer.domain.OrderReturnApply;
import cn.tjau.ifarmer.domain.utilEntity.ReturnApplyCondition;
import cn.tjau.ifarmer.service.ReturnApplyService;
import cn.tjau.ifarmer.service.imp.ReturnApplyServiceImp;
import cn.tjau.ifarmer.utils.R;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/returnApply")
public class ReturnApplyController {
    @Autowired
    ReturnApplyService returnApplyService;

    @GetMapping(value = "/queryByID")
    public R queryByID(@RequestParam(value = "id") Integer id) {
        OrderReturnApply orderReturnApply = returnApplyService.queryByID(id);
        return R.ok().data("apply", orderReturnApply);
    }

    @GetMapping(value = "/delete")
    public R delete(@RequestParam(value = "id") Integer id) {
        boolean flag = returnApplyService.deleteByID(id);
        if (flag) {
            return R.ok();
        }
        return R.error().message("删除失败");
    }

    @PostMapping(value = "/queryList")
    public R queryList(@RequestBody ReturnApplyCondition condition) {
        System.out.println(condition);
        PageInfo<OrderReturnApply> pageInfo = returnApplyService.queryListByCondition(condition);
        return R.ok().data("pageInfo", pageInfo);
    }

    @PostMapping(value = "/add")
    public R add(@RequestBody OrderReturnApply apply) {
        boolean flag = returnApplyService.insert(apply);
        if (flag) {
            return R.ok();
        }
        return R.error().message("添加退货申请失败");
    }

    @PostMapping(value = "/update")
    public R update(@RequestBody OrderReturnApply apply) {
        boolean flag = returnApplyService.update(apply);
        if (flag) {
            return R.ok();
        }
        return R.error().message("修改失败");
    }
}
