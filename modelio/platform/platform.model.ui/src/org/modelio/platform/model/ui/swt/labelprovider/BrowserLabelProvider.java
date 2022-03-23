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
package org.modelio.platform.model.ui.swt.labelprovider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.platform.mda.infra.ModuleI18NService;
import org.modelio.platform.model.ui.plugin.CoreUi;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.platform.model.ui.swt.images.ElementStyler;
import org.modelio.platform.model.ui.swt.images.FragmentImageService;
import org.modelio.platform.model.ui.swt.images.FragmentStyledLabelProvider;
import org.modelio.platform.model.ui.swt.images.IModelioElementLabelProvider;
import org.modelio.platform.ui.CoreFontRegistry;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Browser tree label provider delegating part of its job to <i>extension</i> label providers coming with metamodel fragments.
 */
@objid ("6948d6a1-d63b-11e1-9955-002564c97630")
public class BrowserLabelProvider extends LabelProvider implements IModelioElementLabelProvider {
    @objid ("c13d663c-d63b-11e1-9955-002564c97630")
    protected BrowserLabelService umlLabelService;

    @objid ("ed4047da-504e-450b-8ff8-58ee18765a5e")
    protected static final Font normalFont = CoreFontRegistry.getFont(Display.getCurrent().getSystemFont().getFontData());

    @objid ("8508489f-7996-4cd7-ae4f-983d88315575")
    protected static final Font italicFont = CoreFontRegistry.getModifiedFont(BrowserLabelProvider.normalFont, SWT.ITALIC, 1.0f);

    @objid ("bfeb7ddf-b8a1-4d2d-8a65-f111dcca831d")
    private Map<String, IModelioElementLabelProvider> extensions = new HashMap<>();

    /**
     * Default c'tor.
     */
    @objid ("c13d663d-d63b-11e1-9955-002564c97630")
    public  BrowserLabelProvider() {
        this.umlLabelService = new BrowserLabelService();
    }

    @objid ("c13d663f-d63b-11e1-9955-002564c97630")
    @Override
    public Image getImage(Object obj) {
        // null object special case
        if (obj == null) {
            return null;
        } else if (obj instanceof IProjectFragment) {
            return FragmentImageService.getImage((IProjectFragment) obj);
        } else if (obj instanceof IModelContainer) {
            return ((IModelContainer<?>) obj).getIcon();
        } else if (obj instanceof MObject) {
            IModelioElementLabelProvider ext = getExtensionFor((MObject) obj);
            if (ext != null) {
                return ext.getImage(obj);
            } else if (obj instanceof MObject) {
                return ElementImageService.getIcon((MObject) obj);
            }
        } else {
            for (IModelioElementLabelProvider lbp : this.extensions.values()) {
                Image img = lbp.getImage(obj);
                if (img != null) {
                    return img;
                }
            }
        }
        return null;
    }

    @objid ("8d6903fc-0043-4649-bca9-2d939542e439")
    @Override
    public StyledString getStyledText(Object obj) {
        if (obj == null) {
            return new StyledString("<null>", StyledString.createColorRegistryStyler("red", null));
        } else if (obj instanceof IProjectFragment) {
            return FragmentStyledLabelProvider.getStyledText((IProjectFragment) obj);
        } else if (obj instanceof IModelContainer) {
            return new StyledString(((IModelContainer<?>) obj).getLabel());
        } else if (obj instanceof MObject) {
            IModelioElementLabelProvider ext = getExtensionFor((MObject) obj);
            StyledString styledText = ext != null ? ext.getStyledText(obj) : null;
            if (styledText != null) {
                return styledText;
            } else if (obj instanceof MObject) {
                return this.umlLabelService.getLabel((MObject) obj);
            }
        } else {
            for (IModelioElementLabelProvider lbp : this.extensions.values()) {
                StyledString styledText = lbp.getStyledText(obj);
                if (styledText != null) {
                    return styledText;
                }
            }
        }
        return new StyledString(obj.toString(), StyledString.createColorRegistryStyler("red", null));
    }

    /**
     * Returns the simple label of an object, without style additions. Uses
     * {@link BrowserLabelProvider#getStyledText(Object)}.
     */
    @objid ("7ffe017a-d90b-4845-ad9b-9bfd1a76b146")
    @Override
    public String getText(Object element) {
        return getStyledText(element).getString();
    }

    @objid ("0017ec42-e1fe-100f-85b1-001ec947cd2a")
    @Override
    public boolean showAsReference(Object object) {
        return false;
    }

    @objid ("0b46d26a-7d43-4c69-863c-82bd8a4fc436")
    public void registerExtension(String fragmentName, IModelioElementLabelProvider contentProvider) {
        this.extensions.put(fragmentName, contentProvider);
    }

    @objid ("b9942603-481f-40c1-8c48-18ca68399f65")
    public void unregisterExtension(String fragmentName) {
        this.extensions.remove(fragmentName);
    }

    @objid ("158d8ce2-4d68-4beb-815b-295473e9e8b3")
    public IModelioElementLabelProvider getExtension(String mmFragmentName) {
        return this.extensions.get(mmFragmentName);
    }

    @objid ("d01d864b-6212-4b0f-922b-9260a64df068")
    @Override
    public String getToolTipText(Object obj) {
        if (obj instanceof IProjectFragment) {
            final Throwable downError = ((IProjectFragment) obj).getDownError();
            if (downError != null) {
                return downError.getLocalizedMessage();
            }
        } else if (obj instanceof MObject) {
            IModelioElementLabelProvider ext = getExtensionFor((MObject) obj);
            if (ext != null) {
                return ext.getToolTipText(obj);
            }
        } else {
            for (IModelioElementLabelProvider lbp : this.extensions.values()) {
                String tooltip = lbp.getToolTipText(obj);
                if (tooltip != null) {
                    return tooltip;
                }
            }
        }
        return null;
    }

    @objid ("ca6502d8-7206-4ced-b72d-cd6695db994c")
    private IModelioElementLabelProvider getExtensionFor(MObject obj) {
        return this.extensions.get(obj.getMClass().getOrigin().getName());
    }

    /**
     * This class provide the label of the elements displayed in the UML
     * explorer
     */
    @objid ("c13eecb7-d63b-11e1-9955-002564c97630")
    private static class BrowserLabelService extends DefaultInfrastructureVisitor {
        /**
         * a stack used for recursive calls to
         * {@link #getLabel(Element, boolean)}
         */
        @objid ("f94f1a9e-48bf-4431-928d-7e4cf676b564")
        private final Stack<MObject> elementStack;

        @objid ("d4a49a92-5199-4f9c-9f9b-b93c2a9a0ec3")
        public  BrowserLabelService() {
            this(new Stack<MObject>());
        }

        /**
         * Get the explorer label for the given element.
         * @param featuresVisibility Whether or not to show the visibility in feature's labels.
         * @param namespaceVisibility Whether or not to show the visibility in namespace's
         * labels.
         * @param element The element to get symbol
         * @return The element symbol.
         */
        @objid ("c13eecbe-d63b-11e1-9955-002564c97630")
        public StyledString getLabel(MObject element) {
            // Guard agains't null elements
            if (element == null) {
                return new StyledString("<null>", ElementStyler.getStyler(element));
            }
            
            if (this.elementStack.contains(element)) {
                // loop detected, return the name...
                return new StyledString(element.getName());
            }
            
            // store the element for loop detection, push context
            this.elementStack.push(element);
            
            try {
                // call the visitor
                return (StyledString) element.accept(this);
            } finally {
                this.elementStack.pop();
            }
            
        }

        @objid ("c141fa0c-d63b-11e1-9955-002564c97630")
        @Override
        public Object visitDependency(Dependency theDependency) {
            final ModelElement destination = theDependency.getDependsOn();
            
            assert Dependency.class == theDependency.getMClass().getJavaInterface();
            return visitDependencyLikeObject(theDependency, "depends on", destination);
        }

        @objid ("c1438092-d63b-11e1-9955-002564c97630")
        @Override
        public Object visitElement(Element theElement) {
            return new StyledString(theElement.getClass().getSimpleName(), ElementStyler.getStyler(theElement));
        }

        @objid ("bd29f57c-d755-43f2-9c89-8d5179c03ad4")
        @Override
        public Object visitMatrixValueDefinition(MatrixValueDefinition obj) {
            return new StyledString(CoreUi.I18N.getMessage("MatrixValueDefinition.Values"), ElementStyler.getStyler(obj));
        }

        @objid ("e267f7ae-d7ec-11e1-80f0-001ec947ccaf")
        @Override
        public Object visitMetaclassReference(MetaclassReference theMataclassReference) {
            final String ref = theMataclassReference.getReferencedClassName();
            final Styler styler = ElementStyler.getStyler(theMataclassReference);
            if (ref != null) {
                return new StyledString(ref, styler);
            } else {
                return new StyledString("<null>", styler);
            }
            
        }

        @objid ("c1468dcc-d63b-11e1-9955-002564c97630")
        @Override
        public Object visitModelElement(ModelElement theModelElement) {
            return new StyledString(theModelElement.getName(), ElementStyler.getStyler(theModelElement));
        }

        @objid ("9c0efc34-d828-4a72-ab3c-bc732774ae57")
        @Override
        public Object visitNote(Note obj) {
            final StyledString symbol = new StyledString();
            final Styler style = ElementStyler.getStyler(obj);
            
            NoteType model = obj.getModel();
            String noteType = model == null ? "<none>" : model.getName();
            
            String name = obj.getName();
            if (name.equals("Note") || name.equals(noteType)) {
                name = "";
            }
            
            if (!name.isEmpty()) {
                symbol.append('\'', style);
                symbol.append(name, style);
                symbol.append("\' ", style);
            }
            
            symbol.append(noteType, ElementStyler.getStyler(obj, model));
            return symbol;
        }

        @objid ("b8d91fc4-1734-46b1-a2ac-f792acfb7728")
        @Override
        public Object visitQueryDefinition(QueryDefinition obj) {
            final StyledString symbol = new StyledString("", ElementStyler.getStyler(obj));
            if (obj.getOwnerAsCol() != null) {
                symbol.append(CoreUi.I18N.getMessage("QueryDefinition.Columns"));
            } else if (obj.getOwnerAsLine() != null) {
                symbol.append(CoreUi.I18N.getMessage("QueryDefinition.Lines"));
            } else if (obj.getOwnerAsDepth() != null) {
                symbol.append(CoreUi.I18N.getMessage("QueryDefinition.Depths"));
            } else {
                symbol.append(CoreUi.I18N.getMessage("QueryDefinition.Unknown"));
            }
            return symbol;
        }

        @objid ("93c421c4-c1aa-4c2f-a4e1-f05c359d0027")
        @Override
        public Object visitTagParameter(TagParameter obj) {
            final StyledString symbol = new StyledString(obj.getValue(), ElementStyler.getStyler(obj));
            return symbol;
        }

        @objid ("1761e3bf-e465-42bb-901c-9185f26c6204")
        @Override
        public Object visitTaggedValue(TaggedValue obj) {
            final StyledString symbol = new StyledString();
            
            symbol.append("{");
            
            TagType def = obj.getDefinition();
            if (def == null) {
                symbol.append("<none>");
            } else {
                symbol.append(def.getName(), ElementStyler.getStyler(obj, def));
            }
            
            List<TagParameter> params = obj.getActual();
            if (!params.isEmpty()) {
                symbol.append(" = ");
                boolean first = true;
                for (TagParameter p : params) {
                    if (first) {
                        first = false;
                    } else {
                        symbol.append(", ");
                    }
                    symbol.append(p.getValue(), ElementStyler.getStyler(obj, p));
                }
            }
            
            symbol.append("}");
            return symbol;
        }

        /**
         * Initialize the label service.
         * @param elementStack a stack to use for recursive calls to
         * {@link #getLabel(Element, boolean)}
         */
        @objid ("c13eecbb-d63b-11e1-9955-002564c97630")
         BrowserLabelService(Stack<MObject> elementStack) {
            super();
            this.elementStack = elementStack;
            
        }

        /**
         * Append <code>"(from xxxx)"</code> to the symbol
         * @param symbol the symbol to modify
         * @param srcObj the source object, used to compute the style of
         * <code>'xxxx'</code>
         * @param owner the object to display in <code>'xxxx'</code>
         * @param styler the style of <code>"(from "</code>
         */
        @objid ("7787d580-79b5-418a-8cee-5eee60f8e05c")
        private void appendFrom(final StyledString symbol, ModelElement srcObj, ModelTree owner, final Styler styler) {
            if (owner != null) {
                symbol.append(" (from ", styler);
                symbol.append(owner.getName(), ElementStyler.getStyler(srcObj, owner));
                symbol.append(")", styler);
            }
            
        }

        @objid ("48eaf5dc-f40d-4707-9419-495caec4ed77")
        private static StringBuilder getDependencyVerb(ModelElement dep, String defaultVerb) {
            final StringBuilder stringBuilder = new StringBuilder();
            if (!dep.getExtension().isEmpty()) {
                for (final Stereotype v : dep.getExtension()) {
                    stringBuilder.append(ModuleI18NService.getLabel(v));
            
                    stringBuilder.append(", ");
                }
                // remove last ", "
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            } else {
                stringBuilder.append(defaultVerb);
                /*
                 * if (dep instanceof Usage) stringBuilder.append("uses"); else
                 * if (dep instanceof ElementRealization)
                 * stringBuilder.append("realizes"); else if (dep instanceof
                 * Abstraction) stringBuilder.append("abstracts"); else
                 * stringBuilder.append("depends on");
                 */
            }
            return stringBuilder;
        }

        @objid ("2f696caa-b325-40d6-a497-60b09b4aa466")
        private Object visitDependencyLikeObject(ModelElement theDependency, String mmverb, ModelElement destination) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theDependency);
            final StringBuilder verb = BrowserLabelService.getDependencyVerb(theDependency, mmverb).append(" ");
            
            if (destination != null) {
                symbol.append(verb.toString(), styler);
            
                final StyledString destLabel = new BrowserLabelService(this.elementStack).getLabel(destination);
                symbol.append(destLabel.getString(), ElementStyler.getStyler(theDependency, destination));
            
                ModelTree owner = null;
            
                if (destination instanceof ModelTree) {
                    owner = ((ModelTree) destination).getOwner();
                    appendFrom(symbol, theDependency, owner, styler);
                }
            
            } else {
                symbol.append(verb.toString(), styler);
                symbol.append("<No destination>", styler);
            }
            return symbol;
        }

        @objid ("0f937ecf-e493-448d-92c9-72b0850fe602")
        @Override
        public Object visitImpactLink(ImpactLink theImpactLink) {
            final StyledString symbol = new StyledString();
            final Styler styler = ElementStyler.getStyler(theImpactLink);
            
            ModelElement source = theImpactLink.getImpacted();
            ModelElement target = theImpactLink.getDependsOn();
            
            if (source != null) {
                symbol.append(getLabel(source).toString(), ElementStyler.getStyler(theImpactLink, source));
            }
            
            symbol.append(" -> ", styler);
            
            if (target != null) {
                symbol.append(getLabel(target).toString(), ElementStyler.getStyler(theImpactLink, target));
            }
            
            String causes = " (" + theImpactLink.getCauses().size() + " cause(s))";
            symbol.append(causes, styler);
            return symbol;
        }

    }

}
