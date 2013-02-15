package tk.spop.meta.modelwriter;

import lombok.Data;

@Data
public class MwBody implements MwElement {

    public final String content;

    @Override
    public void wr(Context ctx) {
        ctx.wr(content);
    }

}
