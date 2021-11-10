package chap07.composite_key.embedded_id;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class GrandChildId implements Serializable {

    private ChildId childId;

    @Column(name = "GRANDCHILD_ID")
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrandChildId that = (GrandChildId) o;
        return Objects.equals(childId, that.childId) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(childId, id);
    }

    public GrandChildId(ChildId childId, String id) {
        this.childId = childId;
        this.id = id;
    }
}
