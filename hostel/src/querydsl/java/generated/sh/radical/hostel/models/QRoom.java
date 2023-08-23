package sh.radical.hostel.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoom is a Querydsl query type for Room
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoom extends EntityPathBase<Room> {

    private static final long serialVersionUID = 1063279661L;

    public static final QRoom room = new QRoom("room");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.time.Instant> createdOn = createDateTime("createdOn", java.time.Instant.class);

    public final StringPath lastModifiedBy = createString("lastModifiedBy");

    public final DateTimePath<java.time.Instant> lastModifiedOn = createDateTime("lastModifiedOn", java.time.Instant.class);

    public final ListPath<String, StringPath> names = this.<String, StringPath>createList("names", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath number = createString("number");

    public final StringPath numberOfPeople = createString("numberOfPeople");

    public final StringPath roomId = createString("roomId");

    public QRoom(String variable) {
        super(Room.class, forVariable(variable));
    }

    public QRoom(Path<? extends Room> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoom(PathMetadata metadata) {
        super(Room.class, metadata);
    }

}

