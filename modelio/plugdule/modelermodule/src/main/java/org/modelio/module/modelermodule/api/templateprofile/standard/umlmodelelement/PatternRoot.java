/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.templateprofile.standard.umlmodelelement;

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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link UmlModelElement} with << PatternRoot >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("57d27015-260c-4a65-ab1b-01cc7c60f0d1")
public class PatternRoot {
    @objid ("098ee3ce-4069-4bfd-abbd-87cdef33b295")
    public static final String STEREOTYPE_NAME = "PatternRoot";

    /**
     * The underlying {@link UmlModelElement} represented by this proxy, never null.
     */
    @objid ("700aecee-2600-485a-b321-e94415fc8a47")
    protected final UmlModelElement elt;

    /**
     * Tells whether a {@link PatternRoot proxy} can be instantiated from a {@link MObject} checking it is a {@link UmlModelElement} stereotyped << PatternRoot >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("988bf6cf-5419-4f6b-a8cd-a8d82c7b97ac")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UmlModelElement) && ((UmlModelElement) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, PatternRoot.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UmlModelElement} stereotyped << PatternRoot >> then instantiate a {@link PatternRoot} proxy.
     * 
     * @return a {@link PatternRoot} proxy on the created {@link UmlModelElement}.
     */
    @objid ("7a353367-3c43-47e6-83e4-a25511d9294c")
    public static PatternRoot create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("UmlModelElement");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, PatternRoot.STEREOTYPE_NAME);
        return PatternRoot.instantiate((UmlModelElement)e);
    }

    /**
     * Tries to instantiate a {@link PatternRoot} proxy from a {@link UmlModelElement} stereotyped << PatternRoot >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UmlModelElement
     * @return a {@link PatternRoot} proxy or <i>null</i>.
     */
    @objid ("8dafa04e-ab0f-48bc-95b6-925157bb8f2e")
    public static PatternRoot instantiate(UmlModelElement obj) {
        return PatternRoot.canInstantiate(obj) ? new PatternRoot(obj) : null;
    }

    /**
     * Tries to instantiate a {@link PatternRoot} proxy from a {@link UmlModelElement} stereotyped << PatternRoot >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link UmlModelElement}
     * @return a {@link PatternRoot} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("25308289-855f-4515-b342-a48e6972e67a")
    public static PatternRoot safeInstantiate(UmlModelElement obj) throws IllegalArgumentException {
        if (PatternRoot.canInstantiate(obj))
        	return new PatternRoot(obj);
        else
        	throw new IllegalArgumentException("PatternRoot: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("fd0e8741-e93c-4c64-8231-354ff5dec80f")
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
        PatternRoot other = (PatternRoot) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link UmlModelElement}. 
     * @return the UmlModelElement represented by this proxy, never null.
     */
    @objid ("8e1e9b13-5876-48e8-bb02-09dd8f54e520")
    public UmlModelElement getElement() {
        return this.elt;
    }

    @objid ("8d743e2a-9d40-4958-85ba-ef8e7bfbecb1")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e79e7904-30b0-46c1-9601-7e3843db8487")
    protected PatternRoot(UmlModelElement elt) {
        this.elt = elt;
    }

    @objid ("8ff5a629-6135-49ff-a514-232f119963bc")
    public static final class MdaTypes {
        @objid ("00cb106d-cc4a-4934-9235-a4c175a02547")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2e980023-5efe-4a53-9eaa-ac6313758c7e")
        private static Stereotype MDAASSOCDEP;

        @objid ("96755822-b8d8-45c5-9dd6-e9eefa6a15ef")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5b1af5ef-a15a-4ec4-a792-42e12122e5dc")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ad46ab04-9310-11df-a4cf-0014224f9977");
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
