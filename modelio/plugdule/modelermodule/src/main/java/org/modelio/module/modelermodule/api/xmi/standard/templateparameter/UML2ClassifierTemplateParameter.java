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
 * Proxy class to handle a {@link TemplateParameter} with << UML2ClassifierTemplateParameter >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("7d06e516-f1b3-4177-aff3-431439159472")
public class UML2ClassifierTemplateParameter {
    @objid ("97c35ece-d1a4-4e73-b646-a37e5822b266")
    public static final String STEREOTYPE_NAME = "UML2ClassifierTemplateParameter";

    /**
     * The underlying {@link TemplateParameter} represented by this proxy, never null.
     */
    @objid ("5c72190e-d8f3-455f-8ff9-9eb282981f3a")
    protected final TemplateParameter elt;

    /**
     * Tells whether a {@link UML2ClassifierTemplateParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link TemplateParameter} stereotyped << UML2ClassifierTemplateParameter >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("d953eaf7-340d-44cb-8202-7649e243faa2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof TemplateParameter) && ((TemplateParameter) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClassifierTemplateParameter.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link TemplateParameter} stereotyped << UML2ClassifierTemplateParameter >> then instantiate a {@link UML2ClassifierTemplateParameter} proxy.
     * 
     * @return a {@link UML2ClassifierTemplateParameter} proxy on the created {@link TemplateParameter}.
     */
    @objid ("1f5580c9-b4bb-49d5-b42f-dca907f1b018")
    public static UML2ClassifierTemplateParameter create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("TemplateParameter");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ClassifierTemplateParameter.STEREOTYPE_NAME);
        return UML2ClassifierTemplateParameter.instantiate((TemplateParameter)e);
    }

    /**
     * Tries to instantiate a {@link UML2ClassifierTemplateParameter} proxy from a {@link TemplateParameter} stereotyped << UML2ClassifierTemplateParameter >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a TemplateParameter
     * @return a {@link UML2ClassifierTemplateParameter} proxy or <i>null</i>.
     */
    @objid ("6cd1f017-dafd-44cf-92d0-704ff3d87690")
    public static UML2ClassifierTemplateParameter instantiate(TemplateParameter obj) {
        return UML2ClassifierTemplateParameter.canInstantiate(obj) ? new UML2ClassifierTemplateParameter(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ClassifierTemplateParameter} proxy from a {@link TemplateParameter} stereotyped << UML2ClassifierTemplateParameter >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link TemplateParameter}
     * @return a {@link UML2ClassifierTemplateParameter} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b9db3dd3-34a6-4214-a74e-5a43025fe18b")
    public static UML2ClassifierTemplateParameter safeInstantiate(TemplateParameter obj) throws IllegalArgumentException {
        if (UML2ClassifierTemplateParameter.canInstantiate(obj))
        	return new UML2ClassifierTemplateParameter(obj);
        else
        	throw new IllegalArgumentException("UML2ClassifierTemplateParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("89df54a2-2715-4d4a-836d-cb71ad665337")
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
        UML2ClassifierTemplateParameter other = (UML2ClassifierTemplateParameter) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link TemplateParameter}. 
     * @return the TemplateParameter represented by this proxy, never null.
     */
    @objid ("aa413b7a-d8a5-4e71-a4c8-b6eac28dafc7")
    public TemplateParameter getElement() {
        return this.elt;
    }

    @objid ("5fd9176e-3f3f-401a-9d86-8cb692ef3562")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("97092124-7cfc-4ca1-8eb8-21a4a652729e")
    protected UML2ClassifierTemplateParameter(TemplateParameter elt) {
        this.elt = elt;
    }

    @objid ("7d56bd81-07e4-4088-ae68-252f60dbb3fb")
    public static final class MdaTypes {
        @objid ("80560e7b-9297-49d5-a914-7a77c3e17ec0")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("baabeb2b-a156-4d26-b699-63194c66b57b")
        private static Stereotype MDAASSOCDEP;

        @objid ("abad7c25-b36b-4a5f-9498-64dca37fee3d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("bb86d716-d5c9-467b-aff7-98fb812ea462")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "4a427283-5d09-11df-a996-001302895b2b");
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
