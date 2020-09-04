/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.inputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
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
 * Proxy class to handle a {@link InputPin} with << UML2Value >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("520071db-260b-4339-a5b5-5acd89638735")
public class UML2Value {
    @objid ("842cd727-6a2e-459d-8cc1-0c1faa828520")
    public static final String STEREOTYPE_NAME = "UML2Value";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("5bfc311c-a493-4272-bf47-4c38869aa7c8")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Value proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Value >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("84c41c39-5068-4d62-b674-05ec680d3d13")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Value.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Value >> then instantiate a {@link UML2Value} proxy.
     * 
     * @return a {@link UML2Value} proxy on the created {@link InputPin}.
     */
    @objid ("9f5c3fa7-611c-4208-a7ad-d36c6e38f677")
    public static UML2Value create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Value.STEREOTYPE_NAME);
        return UML2Value.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Value} proxy from a {@link InputPin} stereotyped << UML2Value >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Value} proxy or <i>null</i>.
     */
    @objid ("df959f13-1a6c-4660-9d1f-7668536b5453")
    public static UML2Value instantiate(InputPin obj) {
        return UML2Value.canInstantiate(obj) ? new UML2Value(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Value} proxy from a {@link InputPin} stereotyped << UML2Value >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Value} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("19731f12-9a01-497d-b23f-503bfe408066")
    public static UML2Value safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Value.canInstantiate(obj))
        	return new UML2Value(obj);
        else
        	throw new IllegalArgumentException("UML2Value: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("fa0faddc-40d7-4826-8243-aff87f702da3")
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
        UML2Value other = (UML2Value) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("293938b4-f5aa-4ea3-8e18-2e92e1dc6fa3")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("d1f1bdea-6388-40c4-b990-130c52338b11")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("548e503a-8458-418c-be61-b9caa8737211")
    protected UML2Value(InputPin elt) {
        this.elt = elt;
    }

    @objid ("5619aea8-dfda-4511-8227-6948387e1280")
    public static final class MdaTypes {
        @objid ("98338806-35be-4c1b-a959-4e47022cf34c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7b991da8-0634-494c-9214-1da036348f0f")
        private static Stereotype MDAASSOCDEP;

        @objid ("2bb99a43-81c6-4dc5-96fe-898c92af43e7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1e5eefe1-4493-4668-b3f3-bc35d30e62b3")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "eb8f06b7-de86-11de-905b-001302895b2b");
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
