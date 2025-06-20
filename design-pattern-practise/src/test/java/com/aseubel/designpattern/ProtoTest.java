package com.aseubel.designpattern;

import com.aseubel.designpattern.proto.DeepClone;
import com.aseubel.designpattern.proto.ShallowClone;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Aseubel
 * @date 2025/6/20 下午4:07
 */
@SpringBootTest
public class ProtoTest {
    @Test
    public void cloneTest() {
        ShallowClone cp = new ShallowClone();
        ShallowClone clonecp = (ShallowClone) cp.clone();
        clonecp.show();
        System.out.println(clonecp.getList() == cp.getList());

        DeepClone cp2 = new DeepClone();
        DeepClone clonecp2 = (DeepClone) cp2.clone();
        clonecp2.show();
        System.out.println(clonecp2.getList() == cp2.getList());
    }
}
