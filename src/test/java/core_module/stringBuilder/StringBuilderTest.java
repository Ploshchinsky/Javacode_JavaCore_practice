package core_module.stringBuilder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringBuilderTest {

    @Test
    public void CustomStringBuilderTest() {
        StringBuilder expected = new StringBuilder("ORIGINAL.Update1");
        StringBuilder actual;
        CustomStringBuilder customStringBuilder = new CustomStringBuilder(new StringBuilder("ORIGINAL"));
        customStringBuilder.append(".Update1");
        customStringBuilder.append(".Update2");
        customStringBuilder.append(".Update3");
        //ORIGINAL.Update1.Update2.Update3
        System.out.println(customStringBuilder.getCurrentState());

        //ORIGINAL.Update2.Update3
        customStringBuilder.delete(8, 16);
        System.out.println(customStringBuilder.getCurrentState());

        //ORIGINAL.Update1.Update2.Update3
        System.out.println(customStringBuilder.undo());

        //ORIGINAL.Update1.Update2
        System.out.println(customStringBuilder.undo());

        //ORIGINAL.Update1
        System.out.println(customStringBuilder.undo());

        actual = customStringBuilder.getCurrentState();
        Assertions.assertEquals(expected.toString(), actual.toString());
    }
}
