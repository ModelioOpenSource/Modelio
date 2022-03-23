/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.modelio.api.ui.dnd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;
import org.modelio.api.plugin.Api;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specific implementation of {@link ByteArrayTransfer} for ObRef manipulation.
 */
@objid ("01f4081c-0000-0002-0000-000000000000")
public final class ModelElementTransfer extends ByteArrayTransfer {
    @objid ("01f4081c-0000-0014-0000-000000000000")
    private static final String MYTYPENAME = "model_element_type";

    @objid ("01f4081c-0000-0017-0000-000000000000")
    private static final int MYTYPEID = registerType(MYTYPENAME);

    @objid ("01f4081c-0000-0039-0000-000000000000")
    private static final ModelElementTransfer INSTANCE = new ModelElementTransfer();

    /**
     * Returns the singleton gadget transfer instance.
     * @return the instance of ModelElementTransfer.
     */
    @objid ("01f4081c-0000-001a-0000-000000000000")
    public static ModelElementTransfer getInstance() {
        return INSTANCE;
    }

    /**
     * Avoid explicit instantiation
     */
    @objid ("01f4081c-0000-001f-0000-000000000000")
    private  ModelElementTransfer() {
        
    }

    @objid ("01f4081c-0000-0022-0000-000000000000")
    @Override
    protected int[] getTypeIds() {
        return new int[] {MYTYPEID};
    }

    @objid ("01f4081c-0000-0029-0000-000000000000")
    @Override
    protected String[] getTypeNames() {
        return new String[]{MYTYPENAME};
    }

    @objid ("01f4081c-0000-0030-0000-000000000000")
    @Override
    public void javaToNative(Object object, TransferData transferData) {
        MObject[] selectedElements = (MObject[])object;
        MRef[] refs = new MRef[selectedElements.length];
        for (int i = 0; i < selectedElements.length ; i++) {
            refs[i] = new MRef(selectedElements[i]);
        }
        
        byte[] bytes = toByteArray(refs);
                if (bytes != null)
                   super.javaToNative(bytes, transferData);
        
    }

    @objid ("01f4081c-0000-0034-0000-000000000000")
    @Override
    public Object nativeToJava(TransferData transferData) {
        byte[] bytes = (byte[])super.nativeToJava(transferData);
        return fromByteArray(bytes);
    }

    /**
     * Converts a table of model element references to a byte array.
     * @param refs the model elements to convert.
     * @return a java byte[] containing the converted data if the conversion was successful; otherwise null
     */
    @objid ("2315a269-7dc5-11dd-88d8-0014222a9f79")
    public byte[] toByteArray(final MRef[] refs) {
        byte[] buffer = null;
        
         try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
              ObjectOutput out = new ObjectOutputStream(bos)) {
             
            out.writeObject(refs);
            
            out.close();
            
            buffer = bos.toByteArray();
        } catch (IOException e) {
            Api.LOG.error(e);
        }
        return buffer;
    }

    /**
     * Converts a byte array to a table of model element references.
     * @param buffer the byte array to convert.
     * @return an ObRef[] containing the converted data if the conversion was successful; otherwise null
     */
    @objid ("2328b421-7dc5-11dd-88d8-0014222a9f79")
    public MRef[] fromByteArray(byte[] buffer) {
        if (buffer == null) return null;
         
        MRef[] myData;
         try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer))) {
                         
             Object obj = in.readObject();
            myData = (MRef[])obj;
             
             in.close();
         } catch (ClassNotFoundException e) {
             Api.LOG.error(e);
             return null;
         } catch (IOException e) {
             Api.LOG.error(e);
             return null;
         }
        return myData;
    }

}
