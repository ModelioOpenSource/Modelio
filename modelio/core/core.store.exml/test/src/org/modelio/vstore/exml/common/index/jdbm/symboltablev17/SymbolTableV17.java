package org.modelio.vstore.exml.common.index.jdbm.symboltablev17;

import java.io.IOError;
import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.PrimaryHashMap;
import jdbm.RecordManager;
import jdbm.Serializer;
import org.modelio.vcore.utils.jdbm.LongSerializer;

/**
 * Table to get a unique {@link Long} identifier for an object.
 * @param <T> the mapped object type
 * @author cma
 * @since 3.6.1
 */
@objid ("0d974f7a-b331-4d1e-b4d8-fbc02d44cc72")
public class SymbolTableV17<T> {
    @objid ("8d1f930b-7667-4b25-9f6c-4e40127b6536")
    private Long counter;

    @objid ("9f7c09c7-4d89-4a52-95bf-f5ee74053bb5")
    private final PrimaryHashMap<T, Long> table;

    @objid ("0e245d64-2512-4d9d-a6e9-7b94d9fdfdec")
    private final Serializer<T> serializer;

    @objid ("903982b2-1464-4eb0-ac9f-abea1ed9a8e9")
    private final PrimaryHashMap<Long, T> inverseTable;

    @objid ("61f2e405-6081-4c9f-85d3-e8b76d059a69")
    private final JdbmNamedObject<Long> jdbmCounter;

    @objid ("f4d38442-2dc5-4046-a4e6-51229c7ee756")
    public  SymbolTableV17(RecordManager db, String name, Serializer<T> serializer) throws IOException {
        this.serializer = serializer;
        this.table = db.hashMap(name, serializer, LongSerializer.instance);
        this.inverseTable = db.hashMap(name+"_inverse", LongSerializer.instance, serializer);
        this.jdbmCounter = new JdbmNamedObject<>(db, "", LongSerializer.instance);
        this.counter = this.jdbmCounter.read(1L);
        
    }

    @objid ("fc785c12-d440-4b51-aa52-308ab167dee8")
    public T getValue(long key) throws IOException {
        return this.inverseTable.find(key);
    }

    @objid ("5f179c96-4e34-4202-a173-4165368c71d1")
    public long getOrAddKey(T symbol) throws IOException {
        try {
            Long found = this.table.find(symbol);
            if (found != null) {
                return found;
            }
            
            this.counter++;
            
            while (this.inverseTable.put(this.counter, symbol) != null) {
                this.counter++;
            }
            this.table.put(symbol, this.counter);
            
            return this.counter;
            
        } catch (IOError | InternalError e) {
            throw new IOException(e);
        }
        
    }

    /**
     * @param symbol the symbol to find
     * @return the symbol ID or -1 if not found.
     * @throws IOError on JDBM failure
     * @throws InternalError on JDBM failure
     */
    @objid ("0f8b1389-cf39-4dcc-96cd-cc8fb9784950")
    public long findKey(T symbol) throws IOError, InternalError {
        return this.table.getOrDefault(symbol, -1L);
    }

    @objid ("46a0a353-55f3-41c3-a8e2-5a46eae2d6a4")
    public void commit() throws IOException {
        this.jdbmCounter.write(this.counter);
    }

}
