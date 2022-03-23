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
package org.modelio.vcore.emf;

import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.EcorePackage.Literals;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("3e32595d-bba6-11e1-ac85-001ec947ccaf")
public class ESmPackage extends EPackageImpl {
    @objid ("bbceddb9-bc87-11e1-b576-001ec947ccaf")
    private static boolean isInited = false;

    @objid ("bbceddba-bc87-11e1-b576-001ec947ccaf")
    private boolean isCreated = false;

    @objid ("f15b9862-bea9-11e1-b576-001ec947ccaf")
    private static final String eNS_URI = "http://www.modelio.org/emf/metamodel";

    @objid ("ad31044f-6a3f-47da-8fa0-c434b44d2bf4")
    private EDataType uuidType = null;

    @objid ("a6e46415-90e1-478b-ab2c-d813841c9d80")
    private ISmMetamodelFragment mmFragment;

    @objid ("2e4c7b70-c79c-40be-b601-cecd9c16bc45")
    private SmMetamodel metamodel;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link EcorePackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    @objid ("bbceddbb-bc87-11e1-b576-001ec947ccaf")
    public static ESmPackage init(SmMetamodel metamodel, ISmMetamodelFragment mmFragment) {
        String fragUri = ESmPackage.eNS_URI + "/" + mmFragment.getProvider()+"/"+mmFragment.getName()+"/"+mmFragment.getVersion();
        if (isInited) {
            return (ESmPackage)EPackage.Registry.INSTANCE.getEPackage(fragUri);
        }
        
        // Obtain or create and register package
        ESmPackage thePackage = (ESmPackage)(EPackage.Registry.INSTANCE.get(fragUri) instanceof ESmPackage ? EPackage.Registry.INSTANCE.get(fragUri) : new ESmPackage(metamodel, mmFragment));
        
        isInited = true;
        
        // Create package meta-data objects
        thePackage.createPackageContents();
        
        // Initialize created meta-data
        thePackage.initializePackageContents();
        
        // Register package validator
        EValidator.Registry.INSTANCE.put(thePackage,
                new EValidator.Descriptor()
        {
            @Override
            public EValidator getEValidator()
            {
                return EcoreValidator.INSTANCE;
            }
        });
        
        thePackage.freeze();
        
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(fragUri, thePackage);
        return thePackage;
    }

    @objid ("bbd13ff9-bc87-11e1-b576-001ec947ccaf")
    @Override
    protected ESmClass createEClass(int id) {
        ESmClass c = new ESmClass();
        c.setClassifierID(id);
        getEClassifiers().add(c);
        return c;
    }

    @objid ("bbd13fff-bc87-11e1-b576-001ec947ccaf")
    @Override
    protected void createEAttribute(EClass owner, int id) {
        EAttributeImpl a = (EAttributeImpl)this.ecoreFactory.createEAttribute();
        a.setFeatureID(id);
        owner.getEStructuralFeatures().add(a);
        
    }

    @objid ("bbd14004-bc87-11e1-b576-001ec947ccaf")
    private void createPackageContents() {
        if (this.isCreated) {
            return;
        }
        this.isCreated  = true;
        
        for (SmClass smClass : this.metamodel.getRegisteredMClasses(this.mmFragment)) {
            EClass ec = createEClass(smClass.getId());
            ec.setName(smClass.getName());
            ec.setInstanceClass(smClass.getJavaInterface());
            ec.setInterface(true);
            ec.setAbstract(smClass.isAbstract());
        
            smClass.setEmfAdapter(ec);
        
            EList<EStructuralFeature> eStructuralFeatures = ec.getEStructuralFeatures();
            int i=0;
            for (SmAttribute smAtt : smClass.getSelfAttDef()) {
                ++i;
                // 1) creer sa propre classe EsmAttribute
                // 2) faire esmAtt.setAtt(smAtt)
                //          smAtt.setEAtt(esmAtt)
                ESmAttribute eatt = new ESmAttribute(smAtt);
                eatt.setFeatureID(i);
                eatt.setEType(getPredefinedEType(smAtt.getType()));
                eatt.setName(smAtt.getName());
                eatt.setChangeable(true);
                eatt.setDefaultValue(null);
                eatt.setDerived(false);
                eatt.setID(false);
                eatt.setLowerBound(0);
                eatt.setUpperBound(1);
                eatt.setOrdered(true);
                eatt.setVolatile(false);
                eatt.setUnique(true);
                eatt.setTransient(smAtt.hasDirective(SmDirective.SMCDTRANSIENT));
                eatt.setUnsettable(false);
        
                smAtt.setEmfAdapter(eatt);
                eStructuralFeatures.add(eatt);
        
            }
        
            for (SmDependency smdep : smClass.getSelfDepDef()) {
                ++i;
                ESmDependency edep = new ESmDependency(smdep);
                edep.setFeatureID(i);
                eStructuralFeatures.add(edep);
        
                edep.setChangeable(true);
                edep.setContainerClass(smClass.getJavaInterface());
                edep.setContainment(smdep.isComponent());
                edep.setDerived(false);
                edep.setLowerBound(smdep.getMin());
                edep.setName(smdep.getName());
                edep.setOrdered(true);
                edep.setResolveProxies(true);
                edep.setTransient(smdep.isTransient());
                edep.setUnique(true);
                edep.setUnsettable(true);
                edep.setUpperBound(smdep.getMax());
                edep.setVolatile(false);
            }
        
        }
        
    }

    @objid ("bbd14006-bc87-11e1-b576-001ec947ccaf")
    @SuppressWarnings("static-method")
    private void initializePackageContents() {
        for (SmClass smClass : this.metamodel.getRegisteredMClasses(this.mmFragment)) {
            EClass eclass = smClass.getEmfAdapter();
        
            // initialize inheritance
            SmClass smParent = smClass.getParent();
            if (smParent != null) {
                eclass.getESuperTypes().add(smParent.getEmfAdapter());
            }
        
            // Finish dependency initialization
            for (SmDependency smdep : smClass.getSelfDepDef()) {
                ESmDependency edep = (ESmDependency) smdep.getEmfAdapter();
                SmDependency sym = smdep.getSymetric();
                if (sym != null) {
                    edep.setEType(smdep.getType().getEmfAdapter());
                    edep.setEOpposite(sym.getEmfAdapter());
                }
            }
        }
        
    }

    @objid ("bbd14008-bc87-11e1-b576-001ec947ccaf")
    private EDataType getPredefinedEType(Class<?> type) {
        if (type == Boolean.class) {
            return Literals.EBOOLEAN_OBJECT;
        } else if (type == Integer.class) {
            return Literals.EINTEGER_OBJECT;
        } else if (type == Long.class) {
            return Literals.ELONG_OBJECT;
        } else if (type == String.class) {
            return Literals.ESTRING;
        } else if (type == Double.class) {
            return Literals.EDOUBLE_OBJECT;
        } else if (type == Float.class) {
            return Literals.EFLOAT_OBJECT;
        } else if (type == UUID.class) {
            return getUuidType();
        } else {
            throw new UnsupportedOperationException(type+" is not yet handled.");
        }
        
    }

    @objid ("bbd1400f-bc87-11e1-b576-001ec947ccaf")
    private EDataType getUuidType() {
        if (this.uuidType == null) {
            this.uuidType = createEDataType(-1);
            this.uuidType.setName(UUID.class.getSimpleName());
            this.uuidType.setInstanceClass(UUID.class);
        }
        return this.uuidType;
    }

    /**
     * @param metamodel The metamodel
     * @param mmFragment The metamodel fragment
     */
    @objid ("8eb735f9-df6a-4531-85ae-f3b0121a1565")
    public  ESmPackage(SmMetamodel metamodel, ISmMetamodelFragment mmFragment) {
        this.metamodel = metamodel;
        this.mmFragment = mmFragment;
        
    }

}
