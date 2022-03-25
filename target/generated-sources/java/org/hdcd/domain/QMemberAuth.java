package org.hdcd.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberAuth is a Querydsl query type for MemberAuth
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberAuth extends EntityPathBase<MemberAuth> {

    private static final long serialVersionUID = 1690897381L;

    public static final QMemberAuth memberAuth = new QMemberAuth("memberAuth");

    public final StringPath auth = createString("auth");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updDate = createDateTime("updDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> userAuthNo = createNumber("userAuthNo", Long.class);

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public QMemberAuth(String variable) {
        super(MemberAuth.class, forVariable(variable));
    }

    public QMemberAuth(Path<? extends MemberAuth> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberAuth(PathMetadata metadata) {
        super(MemberAuth.class, metadata);
    }

}

