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
package org.modelio.vstore.exml.common.index.jdbm;

import java.io.IOError;
import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.PrimaryHashMap;
import jdbm.RecordManager;
import jdbm.Serializer;
import org.modelio.vbasic.files.StreamException;

/**
 * Table to get a unique {@link Long} identifier for an object.
 * @param <T> the mapped object type
 * @author cma
 * @since 3.6.1
 */
@objid ("26c4e2f7-cec0-46a4-9eb7-1636681466fd")
class SymbolTable<T> {
    @objid ("60a02f7f-3f56-476b-8384-74f3d71aab89")
    private final RecordManager db;

    @objid ("bf56112c-53d0-49c0-9eb9-c3267e3e03b9")
    private final PrimaryHashMap<T, Long> table;

    @objid ("02d545b1-d065-4e45-9470-74d5b82c1309")
    private final Serializer<T> serializer;

    @objid ("56363c6f-771d-4650-9d7f-c5a0fc6422b4")
    public  SymbolTable(RecordManager db, String name, Serializer<T> serializer) {
        this.db = db;
        this.serializer = serializer;
        this.table = db.hashMap(name, serializer);
        
    }

    @objid ("36999558-6720-452a-8cc0-6a4878ea3485")
    public T getValue(long key) throws IOException {
        return this.db.fetch(key, this.serializer);
    }

    @objid ("5bead96e-a617-49f3-9a3d-8883270f6ad6")
    public long getOrAddKey(T symbol) throws IOException {
        try {
            return this.table.computeIfAbsent(symbol, lsymbol -> {
                try {
                    return this.db.insert(lsymbol, this.serializer);
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
     * @return the symbol ID or -1 if not found.
     * @throws IOError on JDBM failure
     * @throws InternalError on JDBM failure
     */
    @objid ("024b291c-03ee-496f-b9db-589ead2c6432")
    public long findKey(T symbol) throws IOError, InternalError {
        return this.table.getOrDefault(symbol, -1L);
    }

}
