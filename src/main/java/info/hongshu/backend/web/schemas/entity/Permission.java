package info.hongshu.backend.web.schemas.entity;

import info.hongshu.backend.web.schemas.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@PresentationSchema(name = "permission")
public class Permission {
    @PresentationField
    @PresentationColumn(type = ColumnType.LinkShow, width = "50px")
    @PresentationDetailField
    private Long id;

    @PresentationField
    @PresentationColumn(type = ColumnType.LinkUpdate)
    @PresentationFormField(rules = {
            @PresentationRule(required = true, message = "名称是必须的")
    })
    @PresentationDetailField
    private String name;

    @PresentationField(type = FieldType.Datetime)
    @PresentationColumn(width = "140px")
    @PresentationDetailField
    private Date createdTime;

    @PresentationField(type = FieldType.Datetime)
    @PresentationColumn(width = "140px")
    @PresentationDetailField
    private Date updatedTime;
}
