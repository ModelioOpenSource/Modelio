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

package org.modelio.vstore.jdbm.index;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.PrimaryHashMap;
import jdbm.RecordManager;
import org.modelio.vbasic.files.StreamException;

/**
 * Table to get a unique {@link Long} identifier for a String.
 * @author cma
 * @since 3.6.1
 */
@objid ("d115f95b-e860-4701-9aa7-ddbd72b32e6d")
public class StringTable {
    @objid ("9baab6a4-6049-4e1a-842a-172b66def4e3")
    private final RecordManager db;

    @objid ("46b8b524-9933-4a46-9cde-33f130740b81")
    private final PrimaryHashMap<String,Long> table;

    @objid ("c1f21d99-97f2-41eb-9c7d-b2cd465a131b")
    public StringTable(RecordManager db, String name) {
        this.db = db;
        this.table = db.hashMap(name, UTFSerializer.INSTANCE);
    }

    @objid ("565a186e-8517-4dfd-9342-6d9a4d6c34a1")
    public String getString(long key) throws IOException {
        return this.db.fetch(key, UTFSerializer.INSTANCE);
    }

    @objid ("6579124a-7f31-4678-ba5a-1f03d792a837")
    public long getOrAddKey(String symbol) throws IOException {
        try {
            return this.table.computeIfAbsent(symbol, lsymbol -> {
                try {
                    return this.db.insert(lsymbol, UTFSerializer.INSTANCE);
                } catch (IOException e) {
                    throw new StreamException(e);
                }
            });
        } catch (StreamException e) {
            throw e.getWrapped(IOException.class);
        }
    }

    /**
     * @param symbol the symbol to find
     * @return the symbol ID or <i>null</i> if not found.
     * @throws java.io.IOException on JDBM failure
     */
    @objid ("0e99f3b2-3579-4273-a8cd-3f4a313c3032")
    public Long findKey(String symbol) throws IOException {
        return this.table.find(symbol);
    }

}
