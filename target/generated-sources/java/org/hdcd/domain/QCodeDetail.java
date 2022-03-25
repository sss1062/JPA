package org.hdcd.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCodeDetail is a Querydsl query type for CodeDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCodeDetail extends EntityPathBase<CodeDetail> {

    private static final long serialVersionUID = 1668208929L;

    public static final QCodeDetail codeDetail = new QCodeDetail("codeDetail");

    public final StringPath codeName = createString("codeName");

    public final StringPath codeValue = createString("codeValue");

    public final StringPath groupCode = createString("groupCode");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> sortSeq = createNumber("sortSeq", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> updDate = createDateTime("updDate", java.time.LocalDateTime.class);

    public final StringPath useYn = createString("useYn");

    public QCodeDetail(String variable) {
        super(CodeDetail.class, forVariable(variable));
    }

    public QCodeDetail(Path<? extends CodeDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCodeDetail(PathMetadata metadata) {
        super(CodeDetail.class, metadata);
    }

}

