package tk.spop.meta.x;

import tk.spop.meta.x.Constructor.Constructors;

@Constructors({ //
@Constructor(), //
        @Constructor(types = @Arg(String.class)), //
        @Constructor(types = @Arg(int.class)) })
public interface KK {

}
