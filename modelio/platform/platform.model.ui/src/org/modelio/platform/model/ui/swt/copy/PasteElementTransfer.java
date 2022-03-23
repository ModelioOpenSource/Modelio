/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.modelio.platform.model.ui.swt.copy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;
import org.modelio.platform.model.ui.plugin.CoreUi;

/**
 * Paste Element Transfer
 */
@objid ("b2b560d6-c593-48c1-8aa0-67ff843a22ce")
public class PasteElementTransfer extends ByteArrayTransfer {
    @objid ("6b420e7d-995b-4b07-86cf-2003bfeb9b95")
    private static final String MYTYPENAME = "paste_element_type";

    @objid ("65913ad5-cd3d-4fcd-ab84-26cfe9ba4c0b")
    private static final int MYTYPEID = registerType(MYTYPENAME);

    @objid ("975b30b8-0df5-4240-90d9-96c10b212635")
    private static PasteElementTransfer INSTANCE = new PasteElementTransfer();

    /**
     * Avoid explicit instantiation
     */
    @objid ("6af30017-bb84-483e-babd-e996f39d91a0")
    private  PasteElementTransfer() {
        
    }

    /**
     * Returns the singleton gadget transfer instance.
     */
    @objid ("3561254d-d42d-4197-afb3-d38190c52d31")
    public static PasteElementTransfer getInstance() {
        return INSTANCE;
    }

    @objid ("51d3af9b-5810-4884-9e5f-3cdb4204518e")
    @Override
    public void javaToNative(Object object, TransferData transferData) {
        if (object == null || !(object instanceof PasteElementObject)) return;
        
        if (isSupportedType(transferData)) {
            PasteElementObject element = (PasteElementObject) object;     
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutput out = new ObjectOutputStream(bos)) {
                out.writeObject(element);
        
                out.close();
        
                byte[] buffer = bos.toByteArray();
                super.javaToNative(buffer, transferData);
            } catch (IOException e) {
                CoreUi.LOG.error(e);
            }
        }
        
    }

    @objid ("7a34484f-4414-4d6e-8df5-2e7354844403")
    @Override
    public Object nativeToJava(TransferData transferData) {
        byte[] bytes = (byte[])super.nativeToJava(transferData);
        return fromByteArray(bytes);
    }

    @objid ("bb1a6cd5-b3a9-4fd9-940b-52705e499882")
    @Override
    protected int[] getTypeIds() {
        return new int[] {MYTYPEID};
    }

    @objid ("5c6b746c-aebb-4d8f-b0a9-ff202a3aceb2")
    @Override
    protected String[] getTypeNames() {
        return new String[]{MYTYPENAME};
    }

    @objid ("fc05d5d9-d459-4a83-bf19-8cabcf7aca7e")
    public PasteElementObject fromByteArray(byte[] buffer) {
        if (buffer == null) return null;
        
        PasteElementObject myData = null;
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer))) {
            Object obj = in.readObject();
            myData = (PasteElementObject)obj;
        
            in.close();
        } catch (ClassNotFoundException e) {
            CoreUi.LOG.error(e);
            return null;
        } catch (IOException e) {
            CoreUi.LOG.error(e);
            return null;
        }
        return myData;
    }

    @objid ("b143052b-dd15-4639-b2fe-1bb4a4947c21")
    public byte[] toByteArray(PasteElementObject pasteObject) {
        byte[] buffer = null;
        
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(pasteObject);
        
            out.close();
        
            buffer = bos.toByteArray();
        } catch (IOException e) {
            CoreUi.LOG.error(e);
        }
        return buffer;
    }

}
