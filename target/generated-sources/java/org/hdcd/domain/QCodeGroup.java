package org.hdcd.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCodeGroup is a Querydsl query type for CodeGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCodeGroup extends EntityPathBase<CodeGroup> {

    private static final long serialVersionUID = -774317137L;

    public static final QCodeGroup codeGroup = new QCodeGroup("codeGroup");

    public final ListPath<CodeDetail, QCodeDetail> codeDetails = this.<CodeDetail, QCodeDetail>createList("codeDetails", CodeDetail.class, QCodeDetail.class, PathInits.DIRECT2);

    public final StringPath groupCode = createString("groupCode");

    public final StringPath groupName = createString("groupName");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updDate = createDateTime("updDate", java.time.LocalDateTime.class);

    public final StringPath useYn = createString("useYn");

    public QCodeGroup(String variable) {
        super(CodeGroup.class, forVariable(variable));
    }

    public QCodeGroup(Path<? extends CodeGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCodeGroup(PathMetadata metadata) {
        super(CodeGroup.class, metadata);
    }

}

