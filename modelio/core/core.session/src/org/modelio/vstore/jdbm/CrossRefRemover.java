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
package org.modelio.vstore.jdbm;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.PrimaryHashMap;
import jdbm.SerializerInput;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vstore.jdbm.impl.SmObjectDataSerializer;
import org.modelio.vstore.jdbm.index.JdbmIndex;

/**
 * Service that removes existing cross references from a stored object.
 * 
 * @author cma
 * @since 3.6.1
 */
@objid ("e9449fa2-4e1e-46ba-8dc6-b33d8f343edb")
class CrossRefRemover {
    @objid ("684d9a60-cc73-4deb-8252-354aaaca95f0")
    private final PrimaryHashMap<String, byte[]> dbContent;

    @objid ("67756ff0-33a7-451d-bdad-f75f7d2ae697")
    private final SmObjectDataSerializer dataSerializer;

    @objid ("a1be402c-11fe-4e88-b7cb-628478511c3b")
    private final JdbmIndex jdbmIndex;

    @objid ("40dc3c80-be57-4ed5-b143-93cbe59bb8c6")
    public  CrossRefRemover(PrimaryHashMap<String, byte[]> dbContent, SmObjectDataSerializer dataSerializer, JdbmIndex jdbmIndex) {
        this.dbContent = dbContent;
        this.dataSerializer = dataSerializer;
        this.jdbmIndex = jdbmIndex;
        
    }

    /**
     * Remove the given object from cross reference indexes.
     * @param toRemove the reference to remove
     * @throws IOException in case of I/O failure
     */
    @objid ("5a8e76e5-2a68-4996-baf7-36d3927f1e24")
    @SuppressWarnings("resource")
    public void removeCrossRefs(final MRef toRemove) throws IOException {
        byte[] b = this.dbContent.find(toRemove.uuid);
        if (b != null ) {
            this.dataSerializer.parseCrossRefs(new SerializerInput(
                    new ByteArrayInputStream(b)),
                    (dep, targetRef) -> {
                        this.jdbmIndex.removeCrossRef(toRemove, dep, targetRef);
                    });
        }
        
    }

}
