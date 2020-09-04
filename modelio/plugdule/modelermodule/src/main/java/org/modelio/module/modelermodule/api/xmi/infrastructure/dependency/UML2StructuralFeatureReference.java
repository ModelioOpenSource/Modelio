/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << UML2StructuralFeatureReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("8dcc2244-21d0-4ae1-8601-ed7e4b3c648e")
public class UML2StructuralFeatureReference {
    @objid ("d1f7acca-fbaa-43c8-9967-f69853d22739")
    public static final String STEREOTYPE_NAME = "UML2StructuralFeatureReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("0dd2543b-1774-490e-a414-92db70ba3d70")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2StructuralFeatureReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2StructuralFeatureReference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("3f93817e-5fcd-43f7-a8f7-a9e27847d828")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2StructuralFeatureReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2StructuralFeatureReference >> then instantiate a {@link UML2StructuralFeatureReference} proxy.
     * 
     * @return a {@link UML2StructuralFeatureReference} proxy on the created {@link Dependency}.
     */
    @objid ("e670b62a-6d3b-4f88-95c2-183b67a4aff4")
    public static UML2StructuralFeatureReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2StructuralFeatureReference.STEREOTYPE_NAME);
        return UML2StructuralFeatureReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2StructuralFeatureReference} proxy from a {@link Dependency} stereotyped << UML2StructuralFeatureReference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2StructuralFeatureReference} proxy or <i>null</i>.
     */
    @objid ("35eb28fc-9a59-4f36-8c35-7b551d644b40")
    public static UML2StructuralFeatureReference instantiate(Dependency obj) {
        return UML2StructuralFeatureReference.canInstantiate(obj) ? new UML2StructuralFeatureReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2StructuralFeatureReference} proxy from a {@link Dependency} stereotyped << UML2StructuralFeatureReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2StructuralFeatureReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("e30d986d-a685-4f5a-9698-cabe28c4e9c9")
    public static UML2StructuralFeatureReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2StructuralFeatureReference.canInstantiate(obj))
        	return new UML2StructuralFeatureReference(obj);
        else
        	throw new IllegalArgumentException("UML2StructuralFeatureReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("644bb662-d7f6-4004-b8d8-ed294424ebb1")
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
        UML2StructuralFeatureReference other = (UML2StructuralFeatureReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("998200c4-7406-46e3-be7b-d85b3b37fefc")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("0811d5c6-e180-49c3-97fe-375e907f569b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("26598c3a-6383-4832-8909-079db1377e45")
    protected UML2StructuralFeatureReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("fb91f583-43cf-41d9-961d-4c8f8c7ed445")
    public static final class MdaTypes {
        @objid ("44708a02-141f-4b14-aa7d-4e3897042499")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("35a0f6aa-4642-4f25-9358-4d608b00b036")
        private static Stereotype MDAASSOCDEP;

        @objid ("1a1983f9-4122-45dc-a188-d1d7650a70b7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("86ab2ef8-0851-42dc-8911-8bd7491f28f3")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ed0bb1c3-de99-11de-905b-001302895b2b");
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
