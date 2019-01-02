package ja.ServialDemo;

import java.io.*;

/**
 * Created by totalyoung on 2018/10/19.
 */
public class SerialCloneable implements Serializable,Cloneable {

    @Override
    public  Object clone()  {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(this);
            out.close();

            ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bin);
            Object ret = in.readObject();
            in.close();
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
