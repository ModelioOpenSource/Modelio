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

package org.modelio.audit.view.model;

import java.text.AttributedCharacterIterator;
import java.text.CharacterIterator;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.TextStyle;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.extension.IAuditConfigurationPlan;
import org.modelio.audit.plugin.Audit;
import org.modelio.ui.CoreColorRegistry;
import org.modelio.ui.UIColor;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("7c0dd904-e19c-482a-97e7-11de654901fc")
public class DiagnosticFormatter {
    @objid ("f329c63c-59e7-4901-a527-5169a34dab31")
    public static String getMessage(IAuditEntry entry, ILabelProvider labelProvider, IAuditConfigurationPlan auditConfigurationPlan) {
        try {
            String pattern = auditConfigurationPlan.getMessage(entry.getRuleId());
        
            return MessageFormat.format(pattern, DiagnosticFormatter.makeInfos(entry.getLinkedObjects(), labelProvider));
        } catch (@SuppressWarnings("unused") MissingResourceException e) {
            Audit.LOG.warning("auditrules: no entry for rule " + entry.getRuleId() + ".message");
            return "!" + entry.getRuleId() + ".message!";
        }
    }

    @objid ("412fe267-1739-495d-a70d-1af908ac81f7")
    private static Object[] makeStyledInfos(List<Object> linkedObjects, IStyledLabelProvider labelProvider) {
        List<Object> infos = new ArrayList<>();
        for (Object o : linkedObjects) {
            if (o instanceof MObject) {
                MObject element = (MObject) o;
                StyledString label;
                try {
                    label = labelProvider.getStyledText(element);
                    if (label != null) {
                        StyledString s = label;
                        label = DiagnosticFormatter.fixStyle(s);
                    }
        
                } catch (RuntimeException e) {
                    try {
                        label = new StyledString(element.getName());
                    } catch (@SuppressWarnings("unused") RuntimeException e2) {
                        try {
                            label = new StyledString(element.toString());
                        } catch (@SuppressWarnings("unused") RuntimeException e3) {
                            label = new StyledString("!" + e.getMessage() + "!");
                        }
                    }
                }
                infos.add(label);
            } else {
                infos.add(o);
            }
        }
        return infos.toArray();
    }

    @objid ("13211b3c-6e26-4406-abf0-5bc638e6db26")
    public static StyledString getStyledMessage(IAuditEntry entry, IStyledLabelProvider labelProvider, IAuditConfigurationPlan auditConfigurationPlan) {
        try {
            StyledString ret = new StyledString();
            String pattern = auditConfigurationPlan.getMessage(entry.getRuleId());
        
            Object[] styledArgs = DiagnosticFormatter.makeStyledInfos(entry.getLinkedObjects(), labelProvider);
        
            AttributedCharacterIterator it = new MessageFormat(pattern)
                    .formatToCharacterIterator(styledArgs);
        
            it.first();
            while (it.current() != CharacterIterator.DONE) {
                Object argn = it.getAttribute(MessageFormat.Field.ARGUMENT);
                if (argn instanceof Integer) {
                    int start = it.getRunStart(MessageFormat.Field.ARGUMENT);
                    int limit = it.getRunLimit(MessageFormat.Field.ARGUMENT);
        
                    Object arg = styledArgs[(int) argn];
                    if (arg instanceof StyledString) {
                        StyledString styledArg = (StyledString) arg;
                        ret.append(styledArg);
                        it.setIndex(limit - 1);
                    } else {
                        ret.append(it.current());
                        for (int i = start; i < limit - 1; i++) {
                            ret.append(it.next());
                        }
                    }
        
                } else {
                    ret.append(it.current());
                }
        
                it.next();
            }
        
            return ret;
        } catch (@SuppressWarnings("unused") MissingResourceException e) {
            Audit.LOG.warning("auditrules: no entry for rule " + entry.getRuleId() + ".message");
            return new StyledString("!" + entry.getRuleId() + ".message!");
        }
    }

    @objid ("ea8b7907-802c-4dd7-bca1-76abb2b852e9")
    private static Object[] makeInfos(List<Object> linkedObjects, ILabelProvider labelProvider) {
        ArrayList<Object> infos = new ArrayList<>();
        for (Object o : linkedObjects) {
            if (o instanceof MObject) {
                MObject element = (MObject) o;
                String label = "";
                try {
                    label = labelProvider.getText(element);
                } catch (RuntimeException e) {
                    try {
                        label = element.getName();
                    } catch (@SuppressWarnings("unused") RuntimeException e2) {
                        try {
                            label = element.toString();
                        } catch (@SuppressWarnings("unused") RuntimeException e3) {
                            label = ("!" + e.getMessage() + "!");
                        }
                    }
                }
                infos.add(label);
            } else {
                infos.add(o);
            }
        }
        return infos.toArray();
    }

    @objid ("97d7de38-ead3-41a9-a848-a48a31279d5f")
    private static StyledString fixStyle(StyledString s) {
        StyledString ret = new StyledString();
        
        StyleRange[] styleRanges = s.getStyleRanges();
        int strOffset = 0;
        for (StyleRange r : styleRanges) {
            if (strOffset != r.start) {
                ret.append(s.getString().substring(strOffset, r.start), new StyleModifier());
            }
        
            StyleModifier style = new StyleModifier();
            style.setOriginal(r);
            ret.append(s.getString().substring(r.start, r.start + r.length), style);
        
            strOffset = r.start + r.length;
        }
        
        if (strOffset != s.getString().length()) {
            ret.append(s.getString().substring(strOffset, s.getString().length()), new StyleModifier());
        }
        return ret;
    }

    /**
     * Make default constructor private, this is a pure utility class.
     */
    @objid ("43d2339a-4a9f-497b-85d6-3052e092fefe")
    private DiagnosticFormatter() {
        super();
    }

    @objid ("beafcc3f-2b7b-47f5-8daa-5ebc431de277")
    private static class CopyStyler extends Styler {
        @objid ("d1e06a20-72bb-441f-98b3-d6e5d48b182c")
        protected StyleRange range;

        @objid ("0585eaaa-7953-4736-aeb0-1d5ceed2116b")
        @Override
        public void applyStyles(TextStyle textStyle) {
            if (this.range != null) {
                textStyle.background = this.range.background;
                textStyle.borderColor = this.range.borderColor;
                textStyle.borderStyle = this.range.borderStyle;
                textStyle.data = this.range.data;
                textStyle.font = this.range.font;
                textStyle.foreground = this.range.foreground;
                textStyle.metrics = this.range.metrics;
                textStyle.rise = this.range.rise;
                textStyle.strikeout = this.range.strikeout;
                textStyle.strikeoutColor = this.range.strikeoutColor;
                textStyle.underline = this.range.underline;
                textStyle.underlineColor = this.range.underlineColor;
                textStyle.underlineStyle = this.range.underlineStyle;
            }
        }

        @objid ("0081d8ff-8e8c-48d7-9ad9-e67a11c3ce46")
        public void setOriginal(StyleRange range) {
            this.range = range;
        }

    }

    @objid ("f2509345-86f4-426d-8722-019cda8be53a")
    private static final class StyleModifier extends CopyStyler {
        @objid ("3478efe0-e934-4747-8771-e622f2b7d304")
        @Override
        public void applyStyles(TextStyle textStyle) {
            super.applyStyles(textStyle);
            
            Color targetColor = UIColor.HYPERLINK_FG;
            if (textStyle.foreground == null /* || textStyle.foreground.equals(UIColor.MODIFIABLE_ELEMENT) */) {
                textStyle.foreground = targetColor;
            } else {
                RGB target = targetColor.getRGB();
                textStyle.foreground = CoreColorRegistry.getIntermediateColor(textStyle.foreground, target, 0.3f);
            }
        }

    }

}
