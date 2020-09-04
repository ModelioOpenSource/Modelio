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

package org.modelio.core.ui.swt.copy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Paste Element Object
 */
@objid ("02ff7e2f-d080-468b-9d84-c2685f34dbb8")
public class PasteElementObject implements Serializable {
    @objid ("8016adab-185a-4d4a-a9be-6688fa5fe483")
    private static final long serialVersionUID = -3782582279557801105L;

    @objid ("e3ee818d-6629-4f05-8950-a0ceafe19f03")
    private PasteType pasteType;

    @objid ("11f10d31-54c4-45ab-8507-65a3000ed731")
    private List<TransferItem> transferedItem = null;

    @objid ("d4e8c80a-7a0d-4904-a778-5c0b93767467")
    public List<TransferItem> getTransferedItems() {
        return this.transferedItem;
    }

    @objid ("549b1244-90e3-4b69-af7c-5e2819553a23")
    public PasteType getPasteType() {
        return this.pasteType;
    }

    @objid ("b6c05b21-4cbf-40af-bc50-80e548b65fbd")
    public void setPasteType(PasteType pasteType) {
        this.pasteType = pasteType;
    }

    @objid ("d4a778c3-1198-4ffb-9093-7cf57dcc4cfa")
    public PasteElementObject(PasteType pasteType) {
        super();
        this.pasteType = pasteType;
        this.transferedItem = new ArrayList<>();
    }

    @objid ("ee0d9386-0c36-414e-8814-946475ee70b6")
    public void addTransferedItems(TransferItem item) {
        this.transferedItem.add(item);
    }

    @objid ("d5ea8996-b677-4643-a640-64107586ce0d")
    public enum PasteType {
        CUT,
        COPY;
    }

}
