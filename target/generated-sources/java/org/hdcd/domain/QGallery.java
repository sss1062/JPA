package org.hdcd.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGallery is a Querydsl query type for Gallery
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGallery extends EntityPathBase<Gallery> {

    private static final long serialVersionUID = -835030609L;

    public static final QGallery gallery = new QGallery("gallery");

    public final StringPath content = createString("content");

    public final NumberPath<Long> galleryNo = createNumber("galleryNo", Long.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public final DateTimePath<java.time.LocalDateTime> updDate = createDateTime("updDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> view = createNumber("view", Integer.class);

    public final StringPath writer = createString("writer");

    public QGallery(String variable) {
        super(Gallery.class, forVariable(variable));
    }

    public QGallery(Path<? extends Gallery> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGallery(PathMetadata metadata) {
        super(Gallery.class, metadata);
    }

}

