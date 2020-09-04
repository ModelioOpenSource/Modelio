/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.generalization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Generalization} with << UML2GeneralizationSet >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("6f03c25c-fcc3-4bd8-8f86-1f1194db1551")
public class UML2GeneralizationSet {
    @objid ("c3013d93-86b1-422f-ac1c-8b64343a15c8")
    public static final String STEREOTYPE_NAME = "UML2GeneralizationSet";

    @objid ("bf29c31a-854d-4612-9e80-3e11ce3709fd")
    public static final String ID_TAGTYPE = "Id";

    /**
     * The underlying {@link Generalization} represented by this proxy, never null.
     */
    @objid ("5bcffc50-039d-45b0-94ab-5362ce4eaee8")
    protected final Generalization elt;

    /**
     * Tells whether a {@link UML2GeneralizationSet proxy} can be instantiated from a {@link MObject} checking it is a {@link Generalization} stereotyped << UML2GeneralizationSet >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("3985855e-3c57-440b-9c1a-88ee6b2ede02")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Generalization) && ((Generalization) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2GeneralizationSet.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Generalization} stereotyped << UML2GeneralizationSet >> then instantiate a {@link UML2GeneralizationSet} proxy.
     * 
     * @return a {@link UML2GeneralizationSet} proxy on the created {@link Generalization}.
     */
    @objid ("37436cac-06c8-443f-a2c7-edf6608e2c61")
    public static UML2GeneralizationSet create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Generalization");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2GeneralizationSet.STEREOTYPE_NAME);
        return UML2GeneralizationSet.instantiate((Generalization)e);
    }

    /**
     * Tries to instantiate a {@link UML2GeneralizationSet} proxy from a {@link Generalization} stereotyped << UML2GeneralizationSet >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Generalization
     * @return a {@link UML2GeneralizationSet} proxy or <i>null</i>.
     */
    @objid ("e2b41e5d-36bd-4811-9fa6-c91082688f76")
    public static UML2GeneralizationSet instantiate(Generalization obj) {
        return UML2GeneralizationSet.canInstantiate(obj) ? new UML2GeneralizationSet(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2GeneralizationSet} proxy from a {@link Generalization} stereotyped << UML2GeneralizationSet >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Generalization}
     * @return a {@link UML2GeneralizationSet} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3d9302cc-7915-4dde-804c-ba858f5ff1ce")
    public static UML2GeneralizationSet safeInstantiate(Generalization obj) throws IllegalArgumentException {
        if (UML2GeneralizationSet.canInstantiate(obj))
        	return new UML2GeneralizationSet(obj);
        else
        	throw new IllegalArgumentException("UML2GeneralizationSet: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e917b57b-ef02-4f11-a740-15d54f1ab035")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UML2GeneralizationSet other = (UML2GeneralizationSet) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Generalization}. 
     * @return the Generalization represented by this proxy, never null.
     */
    @objid ("bb7d737a-83b6-4e85-b2b4-f50bbabffa42")
    public Generalization getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Id'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("d999cf27-83b6-4550-83bb-e4d8b78513fb")
    public String getId() {
        return this.elt.getTagValue(UML2GeneralizationSet.MdaTypes.ID_TAGTYPE_ELT);
    }

    @objid ("a2367b01-e90d-4eb6-887e-35bb388584bb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'Id'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("ee2f70fa-8671-45c1-8397-ab9ef154d60c")
    public void setId(String value) {
        this.elt.putTagValue(UML2GeneralizationSet.MdaTypes.ID_TAGTYPE_ELT, value);
    }

    @objid ("b01da201-7939-4fea-bc2b-687b42b98c43")
    protected UML2GeneralizationSet(Generalization elt) {
        this.elt = elt;
    }

    @objid ("fd725dfe-dab3-4c76-a9dc-aaa1a63b1b0b")
    public static final class MdaTypes {
        @objid ("430a74b5-b394-4805-97c3-7979bcb07049")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("787e9514-4abe-4be0-b6eb-1bd3325b7b82")
        public static TagType ID_TAGTYPE_ELT;

        @objid ("a324e47e-1a51-4bb2-94d4-0735c4ed6d5b")
        private static Stereotype MDAASSOCDEP;

        @objid ("0443ea00-aa38-4009-aa4a-519774351497")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a1cafad1-e91c-4cd2-aed7-096e941493eb")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "40400bbd-0b5d-11df-8680-001302895b2b");
            ID_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "c2c549ec-26ab-11df-ac88-001302895b2b");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
        }


	static {
		if(ModelerModuleModule.getInstance() != null) {
			init(ModelerModuleModule.getInstance().getModuleContext());
		}
	}
    }

}
