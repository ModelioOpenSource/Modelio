/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.association;

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
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Association} with << UML2Extension >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("91e907b3-23a7-4ab0-a65c-d0a6ef574552")
public class UML2Extension {
    @objid ("03ac89ad-8168-45e0-85af-59aa6a699198")
    public static final String STEREOTYPE_NAME = "UML2Extension";

    /**
     * The underlying {@link Association} represented by this proxy, never null.
     */
    @objid ("a66acd94-8614-43b3-9f7f-7e42d3b63daf")
    protected final Association elt;

    /**
     * Tells whether a {@link UML2Extension proxy} can be instantiated from a {@link MObject} checking it is a {@link Association} stereotyped << UML2Extension >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("66bef80a-38de-4807-9dcb-628084697dd6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Association) && ((Association) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Extension.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Association} stereotyped << UML2Extension >> then instantiate a {@link UML2Extension} proxy.
     * 
     * @return a {@link UML2Extension} proxy on the created {@link Association}.
     */
    @objid ("5d747eb9-e4e6-4a3f-a250-78eb19216402")
    public static UML2Extension create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Association");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Extension.STEREOTYPE_NAME);
        return UML2Extension.instantiate((Association)e);
    }

    /**
     * Tries to instantiate a {@link UML2Extension} proxy from a {@link Association} stereotyped << UML2Extension >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Association
     * @return a {@link UML2Extension} proxy or <i>null</i>.
     */
    @objid ("b84805c6-4b97-428d-aad5-7d84c7ab57dc")
    public static UML2Extension instantiate(Association obj) {
        return UML2Extension.canInstantiate(obj) ? new UML2Extension(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Extension} proxy from a {@link Association} stereotyped << UML2Extension >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Association}
     * @return a {@link UML2Extension} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("af61368d-37d1-4766-887e-59f3b039843e")
    public static UML2Extension safeInstantiate(Association obj) throws IllegalArgumentException {
        if (UML2Extension.canInstantiate(obj))
        	return new UML2Extension(obj);
        else
        	throw new IllegalArgumentException("UML2Extension: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d92f508e-3372-4446-9ad1-a5cfcc29812c")
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
        UML2Extension other = (UML2Extension) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Association}. 
     * @return the Association represented by this proxy, never null.
     */
    @objid ("f315cc52-ba6d-4a11-a53c-9d60c145f844")
    public Association getElement() {
        return this.elt;
    }

    @objid ("69a8fadd-e6c9-4fb3-9fd6-cb3ec9223b04")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("9bf9fef8-2816-493d-b9a9-ad676dd5b0e6")
    protected UML2Extension(Association elt) {
        this.elt = elt;
    }

    @objid ("23d7d6ba-d0db-4e7a-adcd-190ee07503b0")
    public static final class MdaTypes {
        @objid ("966cbdf0-7b5c-40a7-b8c3-1725f876f821")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8e474964-799f-4f38-a47e-f07554a3bd6a")
        private static Stereotype MDAASSOCDEP;

        @objid ("65ed5a39-c489-4b10-8104-fbb2a89eb4da")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0ef9374b-a3c9-4cf8-8f12-c4335c68868b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "4b4745a9-5d0c-11df-a996-001302895b2b");
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
