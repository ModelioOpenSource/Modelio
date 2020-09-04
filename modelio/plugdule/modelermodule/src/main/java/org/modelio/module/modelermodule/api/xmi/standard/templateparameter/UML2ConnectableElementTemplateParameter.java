/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.templateparameter;

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
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link TemplateParameter} with << UML2ConnectableElementTemplateParameter >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ac45a46f-1893-4689-9905-62e6e9724c4f")
public class UML2ConnectableElementTemplateParameter {
    @objid ("e3702826-1b7d-42c6-b9f4-a6e5c8961da5")
    public static final String STEREOTYPE_NAME = "UML2ConnectableElementTemplateParameter";

    /**
     * The underlying {@link TemplateParameter} represented by this proxy, never null.
     */
    @objid ("7f0781c1-08fc-423d-a6d5-1065461550bb")
    protected final TemplateParameter elt;

    /**
     * Tells whether a {@link UML2ConnectableElementTemplateParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link TemplateParameter} stereotyped << UML2ConnectableElementTemplateParameter >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("eb225d0e-2987-41ad-8928-1102a956387b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof TemplateParameter) && ((TemplateParameter) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ConnectableElementTemplateParameter.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link TemplateParameter} stereotyped << UML2ConnectableElementTemplateParameter >> then instantiate a {@link UML2ConnectableElementTemplateParameter} proxy.
     * 
     * @return a {@link UML2ConnectableElementTemplateParameter} proxy on the created {@link TemplateParameter}.
     */
    @objid ("3ad48a8e-0257-4c42-8560-0fb3215b4ddb")
    public static UML2ConnectableElementTemplateParameter create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("TemplateParameter");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ConnectableElementTemplateParameter.STEREOTYPE_NAME);
        return UML2ConnectableElementTemplateParameter.instantiate((TemplateParameter)e);
    }

    /**
     * Tries to instantiate a {@link UML2ConnectableElementTemplateParameter} proxy from a {@link TemplateParameter} stereotyped << UML2ConnectableElementTemplateParameter >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a TemplateParameter
     * @return a {@link UML2ConnectableElementTemplateParameter} proxy or <i>null</i>.
     */
    @objid ("dc8fef38-d2eb-4e33-9bf9-b11d5ff2ca59")
    public static UML2ConnectableElementTemplateParameter instantiate(TemplateParameter obj) {
        return UML2ConnectableElementTemplateParameter.canInstantiate(obj) ? new UML2ConnectableElementTemplateParameter(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ConnectableElementTemplateParameter} proxy from a {@link TemplateParameter} stereotyped << UML2ConnectableElementTemplateParameter >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link TemplateParameter}
     * @return a {@link UML2ConnectableElementTemplateParameter} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("575c2fd6-2909-4d88-bed1-beb85838fc9c")
    public static UML2ConnectableElementTemplateParameter safeInstantiate(TemplateParameter obj) throws IllegalArgumentException {
        if (UML2ConnectableElementTemplateParameter.canInstantiate(obj))
        	return new UML2ConnectableElementTemplateParameter(obj);
        else
        	throw new IllegalArgumentException("UML2ConnectableElementTemplateParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("81e4c8dd-778e-446e-99cb-120b1684da64")
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
        UML2ConnectableElementTemplateParameter other = (UML2ConnectableElementTemplateParameter) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link TemplateParameter}. 
     * @return the TemplateParameter represented by this proxy, never null.
     */
    @objid ("d02e6510-1464-4054-a993-ee292d4a9752")
    public TemplateParameter getElement() {
        return this.elt;
    }

    @objid ("77d3cce3-92ae-4501-ab04-6c27bb3f3fb4")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("75bfe5ba-8308-4f21-b912-4ed720254d17")
    protected UML2ConnectableElementTemplateParameter(TemplateParameter elt) {
        this.elt = elt;
    }

    @objid ("938d54a8-5da0-4ddb-804b-e7b573a5d935")
    public static final class MdaTypes {
        @objid ("6efd9442-1eb2-41fe-b7e4-62987274047f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6ebbc0d1-47ed-430e-9bd3-1691fd05439f")
        private static Stereotype MDAASSOCDEP;

        @objid ("ee96483e-ec39-44d0-b433-44cb291e9077")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("45123ae4-013f-4507-ab23-24e7852a0708")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ff6e0375-5d09-11df-a996-001302895b2b");
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
