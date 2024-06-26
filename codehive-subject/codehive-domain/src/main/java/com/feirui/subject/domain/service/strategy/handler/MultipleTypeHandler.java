package com.feirui.subject.domain.service.strategy.handler;

import com.feirui.subject.common.enums.IsDeletedFlagEnum;
import com.feirui.subject.common.enums.SubjectTypeEnum;
import com.feirui.subject.domain.bo.SubjectAnswerBO;
import com.feirui.subject.domain.bo.SubjectInfoBO;
import com.feirui.subject.domain.bo.SubjectOptionBO;
import com.feirui.subject.domain.convert.MultipleSubjectConverter;
import com.feirui.subject.infra.basic.entity.SubjectMultiple;
import com.feirui.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 多选策略类
 */
@Component
public class MultipleTypeHandler implements SubjectTypeHandler {
    @Resource
    private SubjectMultipleService subjectMultipleService;

    @Override
    public SubjectTypeEnum getHandlerType() {
        return SubjectTypeEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        // 多选题目的插入
        List<SubjectMultiple> subjectMultipleList = new LinkedList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectMultiple subjectMultiple = MultipleSubjectConverter.INSTANCE.convert(option);
            subjectMultiple.setSubjectId(subjectInfoBO.getId());
            subjectMultiple.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getStatus());
            subjectMultipleList.add(subjectMultiple);
        });
        subjectMultipleService.saveBatch(subjectMultipleList);
    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        // 根据题目id查出多选题答案数据
        List<SubjectMultiple> subjectMultiples = subjectMultipleService.getBySubjectId(subjectId);
        // 属性值复制到SubjectAnswer，再设置到SubjectOption上
        List<SubjectAnswerBO> subjectAnswerBOList = MultipleSubjectConverter.INSTANCE
                .convert(subjectMultiples);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
