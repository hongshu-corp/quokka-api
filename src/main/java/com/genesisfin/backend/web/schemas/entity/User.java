package com.genesisfin.backend.web.schemas.entity;

import com.genesisfin.backend.web.schemas.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@PresentationSchema(name = "user")
public class User {

    @PresentationField
    @PresentationColumn(type = ColumnType.LinkShow, width = "50px")
    @PresentationDetailField
    private Long id;

    @PresentationField
    @PresentationFormField(placeholder = "请输入名字", rules = {
            @PresentationRule(required = true, message = "名字是必须的")
    })
    @PresentationColumn(type = ColumnType.LinkUpdate, width = "100px")
    @PresentationDetailField
    @PresentationSearchField
    private String name;

    @PresentationField(type = FieldType.Map, options = {
            @PresentationOption(label = "msn", value = "tsuijy@msn.com"),
            @PresentationOption(label = "gmail", value = "tsuijy@gmail.com")
    })
    @PresentationColumn(minWidth = "100px")
    @PresentationFormField(type = FormFieldType.Select, placeholder = "邮箱")
    @PresentationDetailField
    @PresentationSearchField
    private String email;

    @PresentationField(type = FieldType.Password)
    @PresentationFormField(rules = {
            @PresentationRule(required = true, message = "密码是必须的")
    })
    private String password;

    @PresentationField(type = FieldType.Datetime)
    @PresentationColumn(width = "140px")
    @PresentationDetailField
    private Date createdTime;

    @PresentationField(type = FieldType.Datetime)
    @PresentationDetailField
    private Date updatedTime;

    @PresentationField(type = FieldType.Datetime)
    @PresentationFormField(type = FormFieldType.Checkbox, dataUrl = "/roles", method = "get")
    private int[] roles;
}
