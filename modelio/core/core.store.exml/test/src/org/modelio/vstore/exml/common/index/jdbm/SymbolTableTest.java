package org.modelio.vstore.exml.common.index.jdbm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel.MofBuilder;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;
import org.modelio.vcore.smkernel.meta.mof.MofSmClass;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.model.ObjId;
import org.modelio.vstore.exml.common.model.ObjIdName;

/**
 * {@link SymbolTable} unit test.
 * @author cma
 */
@objid ("9b60dd15-a3c2-4457-80fc-ac5e0663d5b4")
@SuppressWarnings("javadoc")
public class SymbolTableTest {
    @objid ("f3850063-f3bf-4db1-b903-5355348e3bcf")
    @ClassRule
    public static TemporaryFolder folder = new TemporaryFolder();

    @objid ("84cdb10f-0165-44d5-98aa-36f5394106eb")
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

    @objid ("7fd850bf-57fe-458a-876a-e73b8e21d558")
    @Test
    public void testObjIdSymbolTable() throws IOException {
        TestMetamodel mm = new TestMetamodel();
        
        
        ObjId oa = new ObjId(mm.classCls, "AAA");
        long a;
        
        RecordManager db = RecordManagerFactory.createRecordManager(folder.getRoot().toString()+"/index1");
        try {
            SymbolTable<ObjId> tbl = new SymbolTable<>(db, "ObjIdSymbol", new ObjIdSerializer(mm.mm));
        
            a = tbl.getOrAddKey(oa);
            assert (a > 0);
        
            assert (tbl.findKey(oa) == a);
            assert (tbl.getValue(a).equals(oa));
        
        } finally {
            db.close();
        }
        
        // reopen base and test it
        db = RecordManagerFactory.createRecordManager(folder.getRoot().toString()+"/index1");
        try {
            SymbolTable<ObjId> tbl = new SymbolTable<>(db, "ObjIdSymbol", new ObjIdSerializer(mm.mm));
        
            assert (tbl.findKey(oa) == a);
            assert (tbl.getValue(a).equals(oa));
        } finally {
            db.close();
        }
    }

    @objid ("4e187c69-1662-463a-95c7-22a3c9fed4f9")
    @Test
    public void test3() throws IOException, IndexException {
        TestMetamodel mm = new TestMetamodel();
        
        
        RecordManager db = RecordManagerFactory.createRecordManager(folder.getRoot().toString()+"/index1");
        try {
            SymbolTable<ObjId> objIdTable = new SymbolTable<>(db, "ObjIdTable", new ObjIdSerializer(mm.mm));
            SymbolTable<String> symbolTable = new SymbolTable<>(db, "SymbolTable", UTFSerializer.INSTANCE);
            
            
            CmsNodeIndex cmsIdx = new CmsNodeIndex(db, objIdTable);
            UserNodeIndex userIdx = new UserNodeIndex(db, symbolTable, objIdTable);
            
            ObjIdName cl1 = new ObjIdName(mm.classCls, "C1", "id_c1");
            ObjIdName cl2 = new ObjIdName(mm.classCls, "C2", "id_c2");
            ObjIdName cl3 = new ObjIdName(mm.classCls, "C3", "id_c3");
            ObjIdName att1_2 = new ObjIdName(mm.attCls, "att1_2", "id_c1.att1_2");
            ObjIdName att1_3 = new ObjIdName(mm.attCls, "att1_3", "id_c1.att1_3");
            ObjIdName att2_3 = new ObjIdName(mm.attCls, "att2_3", "id_c1.att2_3");
            
            cmsIdx.addCmsNode(cl1);
            cmsIdx.addCmsNode(cl2);
            cmsIdx.addCmsNode(cl3);
            
            cmsIdx.addObject(cl1.toObjId(), att1_2);
            cmsIdx.addObject(cl1.toObjId(), att1_3);
            cmsIdx.addObject(cl2.toObjId(), att2_3);
            
            userIdx.addUsed(att1_2.toObjId(), "Type", cl2.toObjId());
            userIdx.addUsed(att1_3.toObjId(), "Type", cl3.toObjId());
            userIdx.addUsed(att2_3.toObjId(), "Type", cl3.toObjId());
            
            db.commit();
            
            Collection<String> allClasses = cmsIdx.getByMClass(mm.classCls);
            //assert(allClasses.containsAll(Arrays.asList(cl1.id, cl2.id, cl3.id)));
            assert (allClasses.contains(cl1.id));
            assert (allClasses.contains(cl2.id));
            assert (allClasses.contains(cl3.id));
            assert (!allClasses.contains(att1_2.id));
            assert (!allClasses.contains(att1_3.id));
            assert (!allClasses.contains(att2_3.id));
            
            ArrayList<ObjId> l = new ArrayList<>();
            cmsIdx.getCmsNodeContent(cl1.toObjId()).forEach(l::add);
            System.out.println(""+cl1+" contains: "+l);
            assert (l.contains(att1_2.toObjId()));
            assert (l.contains(att1_3.toObjId()));
            assert (! l.contains(att2_3.toObjId()));
            
            Collection<ObjId> cl3Users = userIdx.getObjectUsers(cl3.toObjId(), "Type");
            assert (cl3Users.contains(att2_3.toObjId()));
            assert (cl3Users.contains(att1_3.toObjId()));
            assert (!cl3Users.contains(att1_2.toObjId()));
            assert (!cl3Users.contains(cl1.toObjId()));
            assert (!cl3Users.contains(cl2.toObjId()));
            
        
        } finally {
            db.close();
        }
        
        // reopen base and test it
        db = RecordManagerFactory.createRecordManager(folder.getRoot().toString()+"/index1");
        try {
            SymbolTable<ObjId> tbl = new SymbolTable<>(db, "ObjIdSymbol", new ObjIdSerializer(mm.mm));
        
        } finally {
            db.close();
        }
    }

    /**
     * Small MOF test metamodel.
     * @author cma
     */
    @objid ("ae4006a2-c093-4e75-bad2-78a09e9facba")
    private static class TestMetamodel {
        @objid ("e01b1e92-0d74-41be-95bf-803275f04c15")
        public final MofSmClass classCls;

        @objid ("46ed17cb-5b02-482b-924d-d0130d30ccdb")
        public final MofSmClass attCls;

        @objid ("a085f5cb-1275-4390-8260-241a46dcd35e")
        public final MofMetamodel mm;

        @objid ("48780530-dd46-4521-9e52-d180743107e6")
        public TestMetamodel() {
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
