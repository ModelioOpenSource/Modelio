/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.analyst.standard.staticdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.StaticDiagram;
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
 * Proxy class to handle a {@link StaticDiagram} with << risk_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("7610605a-be93-4205-972e-98cbcf23a5a6")
public class RiskDiagram {
    @objid ("8fe2b180-9089-4432-9dd6-12a802b60e7c")
    public static final String STEREOTYPE_NAME = "risk_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("efa16597-2735-4005-a439-0d027ebe6d8d")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link RiskDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << risk_diagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e094c094-b56c-4f64-abbe-d01f1f2efb94")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RiskDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << risk_diagram >> then instantiate a {@link RiskDiagram} proxy.
     * 
     * @return a {@link RiskDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("e68663a6-5b44-4f09-8d51-a2d40ca6140c")
    public static RiskDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, RiskDiagram.STEREOTYPE_NAME);
        return RiskDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link RiskDiagram} proxy from a {@link StaticDiagram} stereotyped << risk_diagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link RiskDiagram} proxy or <i>null</i>.
     */
    @objid ("dd195feb-e221-4d22-aa99-480089aa1415")
    public static RiskDiagram instantiate(StaticDiagram obj) {
        return RiskDiagram.canInstantiate(obj) ? new RiskDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link RiskDiagram} proxy from a {@link StaticDiagram} stereotyped << risk_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link RiskDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("523ab9f3-ed41-4fa0-9734-04f068fb4f69")
    public static RiskDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (RiskDiagram.canInstantiate(obj))
        	return new RiskDiagram(obj);
        else
        	throw new IllegalArgumentException("RiskDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2a94916c-efc7-461d-9479-38bd27bd1a53")
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
        RiskDiagram other = (RiskDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("5223f1d3-f84f-4c58-a9ab-1e9f46a33509")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("b810ed8c-1dd5-437e-bbf1-16f16991621a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("bdee384c-cf7f-4456-a915-4ada961db3d8")
    protected RiskDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("1d8c991a-287c-416d-a3d1-46202e80f109")
    public static final class MdaTypes {
        @objid ("22d31a6c-19a1-43b8-bcf6-cabeaa5ac168")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4dfbd6a6-50bd-4a19-8650-134bee0aa4e9")
        private static Stereotype MDAASSOCDEP;

        @objid ("ceb1eff8-b2e7-4003-bf3a-4c5344655f9a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("fa526bde-0b6a-429b-8f1c-d186b0fd848e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "61222898-ff82-4681-a82f-a8f14c479dd1");
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
