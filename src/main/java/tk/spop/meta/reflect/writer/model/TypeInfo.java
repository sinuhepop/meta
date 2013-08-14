package tk.spop.meta.reflect.writer.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TypeInfo {

    private String name;
    private List<TypeInfo> types = new ArrayList<>();

}
