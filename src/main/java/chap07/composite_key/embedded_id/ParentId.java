package chap07.composite_key.embedded_id;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
public class ParentId implements Serializable {

    private String id;

    public ParentId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParentId parentId = (ParentId) o;
        return Objects.equals(id, parentId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
