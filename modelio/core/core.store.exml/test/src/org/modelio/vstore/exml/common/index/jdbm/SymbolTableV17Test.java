package org.modelio.vstore.exml.common.index.jdbm;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.modelio.vbasic.debug.Chronometer;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel.MofBuilder;
import org.modelio.vcore.smkernel.meta.mof.MofSmClass;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;
import org.modelio.vstore.exml.common.index.jdbm.symboltablev17.SymbolTableV17;
import org.modelio.vstore.exml.common.model.ObjId;

/**
 * {@link SymbolTable} unit test.
 * @author cma
 */
@objid ("dd909a0d-9f80-4b12-a13b-9ace18d4f47f")
@SuppressWarnings("javadoc")
public class SymbolTableV17Test {
    @objid ("134f8927-e2ff-475b-9417-51d2ce006d87")
    @ClassRule
    public static TemporaryFolder folder = new TemporaryFolder();

    @objid ("1ee4c4b3-3169-4957-baec-9e5b5e7faaed")
    private static final int COUNT = 200_000;

    @objid ("9df659ec-936a-4425-baaf-7a492e1821b9")
    @Test
    public void testStringSymbolTable() throws IOException {
        RecordManager db = RecordManagerFactory.createRecordManager(folder.getRoot().toString()+"/index1");
        try {
            SymbolTable<String> tbl = new SymbolTable<>(db, "StringSymbol", UTFSerializer.INSTANCE);
        
            long a = tbl.getOrAddKey("A");
            assert (a > 0);
        
            assert (tbl.findKey("A") == a);
        } finally {
            db.close();
        }
        
    }

    @objid ("567f25d3-7d16-47b8-ac0d-f9fc8c40c32a")
    @Test
    public void testObjIdSymbolTable() throws IOException {
        TestMetamodel mm = new TestMetamodel();
        Chronometer chrono = new Chronometer();
        
        
        
        final String indexPath = folder.getRoot().toString()+"/index1";
        RecordManager db = RecordManagerFactory.createRecordManager(indexPath);
        try {
            SymbolTable<ObjId> tbl = new SymbolTable<>(db, "ObjIdSymbol", new ObjIdSerializer(mm.mm));
        
            int i = 0;
            for (i = 0; i < COUNT; i++) {
                ObjId oa = new ObjId(mm.classCls, "UUID-"+i);
                long a = tbl.getOrAddKey(oa);
                assert (a > 0);
        
                assert (tbl.findKey(oa) == a);
                assert (tbl.getValue(a).equals(oa));
            }
            chrono.logNext("1- Added "+COUNT+" elements");
            
            db.commit();
            chrono.logNext("1- Commit");
        } finally {
            db.close();
        }
        chrono.logTotal("1- Total");
        
        chrono = new Chronometer();
        // reopen base and test it
        db = RecordManagerFactory.createRecordManager(indexPath);
        try {
            SymbolTable<ObjId> tbl = new SymbolTable<>(db, "ObjIdSymbol", new ObjIdSerializer(mm.mm));
        
            ObjId val = new ObjId(mm.classCls, "UUID-"+1);
            long i = 0;
            long lid = 0;
            while (lid != -1) {
                val = new ObjId(mm.classCls, "UUID-"+i);
                lid = tbl.findKey(val);
                if (lid != -1) {
                    i++;
                    assert (tbl.getValue(lid).equals(val));
                }
            }
            chrono.logNext("2- Read "+i+" elements");
            assert (i == COUNT) : i;
        } finally {
            db.close();
        }
        chrono.logTotal("2- Total");
        
    }

    @objid ("b57c611c-8726-408f-80f4-30cbd731232b")
    @Test()
    public void testObjIdSymbolTableV16() throws IOException {
        TestMetamodel mm = new TestMetamodel();
        Chronometer chrono = new Chronometer();
        
        String indexPath = folder.getRoot().toString()+"/indexv16";
        RecordManager db = RecordManagerFactory.createRecordManager(indexPath);
        try {
            SymbolTableV17<ObjId> tbl = new SymbolTableV17<>(db, "ObjIdSymbol", new ObjIdSerializer(mm.mm));
        
            int i = 0;
            for (i = 0; i < COUNT; i++) {
                ObjId oa = new ObjId(mm.classCls, "UUID-"+i);
                long a = tbl.getOrAddKey(oa);
                assert (a > 0);
        
                assert (tbl.findKey(oa) == a);
                assert (tbl.getValue(a).equals(oa));
            }
            chrono.logNext("V16 - 1- Added "+i+" elements");
        
            tbl.commit();
            db.commit();
            chrono.logNext("V16 - 1- Commit");
        } finally {
            db.close();
        }
        chrono.logTotal("V16 - 1- Total");
        
        // reopen base and test it
        chrono = new Chronometer();
        db = RecordManagerFactory.createRecordManager(indexPath);
        try {
            SymbolTable<ObjId> tbl = new SymbolTable<>(db, "ObjIdSymbol", new ObjIdSerializer(mm.mm));
        
            ObjId val = new ObjId(mm.classCls, "UUID-"+1);
            long i = 0;
            long lid = 0;
            while (lid != -1) {
                val = new ObjId(mm.classCls, "UUID-"+i);
                lid = tbl.findKey(val);
                if (lid != -1) {
                    i++;
                    assert (tbl.getValue(lid).equals(val));
                }
            }
            chrono.logNext("V16 - 2- Read "+i+" elements");
            assert (i == COUNT) : i;
            
        } finally {
            db.close();
        }
        chrono.logTotal("V16 - 2- Total");
        
    }

    /**
     * Small MOF test metamodel.
     * @author cma
     */
    @objid ("c4f9f5e1-41c4-4472-bab3-80c7f0a7bf49")
    private static class TestMetamodel {
        @objid ("76ccd9d0-236a-4b4e-8d7d-a28cda644900")
        public final MofSmClass classCls;

        @objid ("d6eabd9e-e6e5-4cf2-bbbd-716ec86dc033")
        public final MofSmClass attCls;

        @objid ("cd63dc0e-7188-48e7-9b39-aaf4378042ad")
        public final MofMetamodel mm;

        @objid ("435ec11b-5007-45d2-8166-30aeca81b00a")
        public  TestMetamodel() {
            this.mm = new MofMetamodel();
            try (MofBuilder mmBuilder = this.mm.builder();) {
                this.classCls = mmBuilder.createClass("Class", "mmFrag1", true).build();
                this.attCls = mmBuilder.createClass("Att", "mmFrag1", false).build();
            
                mmBuilder.createDep("OwnedAtt")
                .setSource(this.classCls)
                .setTarget(this.attCls)
                .setCardinality(0, -1)
                .setComposition()
                .createOpposite("Owner", 1, 1)
                .build();
            
                mmBuilder.createDep("OwnedClass")
                .setSource(this.classCls)
                .setTarget(this.classCls)
                .setCardinality(0, -1)
                .setComposition()
                .createOpposite("Owner", 0, 1)
                .build();
            
                mmBuilder.createDep("Type")
                .setSource(this.classCls)
                .setTarget(this.attCls)
                .setCardinality(0, 1)
                .createOpposite("Typed", ob -> {
                    ob.setCardinality(0, -1);
                    ob.setNoPartOf();
                    ob.addFlag(SmDirective.SMCDDYNAMIC);
                })
                .build();
            }
            
        }

    }

}
