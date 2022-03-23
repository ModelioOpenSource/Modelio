package org.modelio.vstore.exml.common.index.jdbm.symboltablev17;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.RecordManager;
import jdbm.Serializer;

@objid ("0ae19d19-bf95-46e4-8d2f-0145efb72357")
public class JdbmNamedObject<T> {
    @objid ("cd3993d5-c9b1-4c08-8e35-066565b99c64")
    private final String objectKey;

    @objid ("88bf2752-c56f-45ad-bf5a-e322bcea6a05")
    private final RecordManager db;

    @objid ("6d0b8f33-4cac-4a8a-b55f-8973547461fe")
    private final Serializer<T> serializer;

    @objid ("76cb261e-8ab3-4562-9e05-c14f26cefba9")
    public  JdbmNamedObject(RecordManager db, String objectKey, Serializer<T> serializer) {
        this.db = db;
        this.objectKey = objectKey;
        this.serializer = serializer;
        
    }

    @objid ("d32cf9da-977a-44e2-a196-5104db9aef39")
    public T read(T defaultVal) throws IOException {
        long recId = this.db.getNamedObject(this.objectKey);
        if (recId == 0) {
            return defaultVal;
        } else {
            return this.db.fetch(recId, this.serializer);
        }
        
    }

    @objid ("28b73902-63f4-4447-9e8e-6e53ab60b781")
    public void write(T value) throws IOException {
        long recId = this.db.getNamedObject(this.objectKey);
        if (recId == 0) {
            recId = this.db.insert(value, this.serializer);
            this.db.setNamedObject(this.objectKey, recId);
        } else {
            this.db.update(recId, value,this.serializer);
        }
        this.db.commit();
        
    }

}
