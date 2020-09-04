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
 * Proxy class to handle a {@link StaticDiagram} with << dictionary_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3ccfaba8-70d5-49d8-a70a-4cc435f8822a")
public class DictionaryDiagram {
    @objid ("b432d8ae-533d-49df-848c-388cf9cfaa8e")
    public static final String STEREOTYPE_NAME = "dictionary_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("e1878dbe-19eb-4ea4-991e-5c35c1cecec1")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link DictionaryDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << dictionary_diagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("210e6a76-3517-4c0d-b696-09f2e4fa30c1")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, DictionaryDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << dictionary_diagram >> then instantiate a {@link DictionaryDiagram} proxy.
     * 
     * @return a {@link DictionaryDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("0766a6c8-59c7-4c09-8c4b-3f250994aa36")
    public static DictionaryDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, DictionaryDiagram.STEREOTYPE_NAME);
        return DictionaryDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link DictionaryDiagram} proxy from a {@link StaticDiagram} stereotyped << dictionary_diagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link DictionaryDiagram} proxy or <i>null</i>.
     */
    @objid ("bbc20039-c94b-4967-a03d-145472c361e6")
    public static DictionaryDiagram instantiate(StaticDiagram obj) {
        return DictionaryDiagram.canInstantiate(obj) ? new DictionaryDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link DictionaryDiagram} proxy from a {@link StaticDiagram} stereotyped << dictionary_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link DictionaryDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("daa0fe38-5ae6-4ccf-ad60-3ce042d6be08")
    public static DictionaryDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (DictionaryDiagram.canInstantiate(obj))
        	return new DictionaryDiagram(obj);
        else
        	throw new IllegalArgumentException("DictionaryDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("cd80cbbc-3334-4aa4-83bd-01cc8c0baf08")
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
        DictionaryDiagram other = (DictionaryDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("76415299-df94-4c62-a963-c7352073bdd0")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("b0f4268d-4dce-4833-91f3-19c26b0ab49a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("1ce2711a-660d-4d10-94ec-83c062801713")
    protected DictionaryDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("e3bf2946-f53e-4175-8a51-d544c543a4b6")
    public static final class MdaTypes {
        @objid ("461e9496-19ba-4231-b9a3-e90264da1340")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9e360ee1-ee76-46b0-9304-488342fe666d")
        private static Stereotype MDAASSOCDEP;

        @objid ("c1ed1c0a-0ddb-40ac-a07d-e2026f89e69e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9fe8aa2d-b282-4046-9de3-6f8ea22db40c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0c02-0000-000000000000");
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
