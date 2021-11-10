package chap07.composite_key.embedded_id;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class ChildId implements Serializable {

    private String parentId; // @MapsId("parentId")로 매핑

    @Column(name = "CHILD_ID")
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildId childId = (ChildId) o;
        return Objects.equals(parentId, childId.parentId) &&
                Objects.equals(id, childId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, id);
    }

    public ChildId(String parentId, String id) {
        this.parentId = parentId;
        this.id = id;
    }
}
