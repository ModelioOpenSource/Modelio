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
package org.modelio.diagram.elements.core.link.anchors;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;

/**
 * Helper class to choose a good anchor reference point one or more set of points.
 * <p>
 * A good anchor reference point is {@link Point} that is:<ul>
 * <li> non null
 * <li> non zero (0, 0)
 * <li> not a forbidden point, usually the center of a figure.
 * </ul>
 * 
 * @author cma
 * @since 5.1
 */
@objid ("442a6a23-fd10-4bfb-b402-8500f751bc5c")
public class AnchorRefHelper {
    @objid ("2f2354c5-724c-4e15-80c0-724f0462bc5e")
    private  AnchorRefHelper() {
        // no instance
    }

    /**
     * Choose the first good point in the given list
     * @param candidates the point candidates
     * @param first it true begin at the list start. If false process from the end in the reverse order.
     * @param len the number of points to test in the candidate list.
     * @param forbidden a bad reference point to avoid
     * @return the found good reference point, null if none .
     */
    @objid ("c8b0e800-fa16-4bdf-8710-71d36beafda9")
    public static Point findGoodAnchorRef(List<? extends Point> candidates, boolean first, int len, Point forbidden) {
        if (candidates==null)
            return null;
        if (candidates.isEmpty())
            return null;
        
        if (first) {
            int stop = Math.min(len, candidates.size());
            for (int i = 0; i < stop; i++) {
                Point candidate = candidates.get(i);
                if (isGoodAnchorRef(candidate, forbidden))
                    return candidate;
            }
        } else {
            int i = candidates.size() - 1;
            int stop = i - len;
            for (; i > stop && i >= 0; i--) {
                Point candidate = candidates.get(i);
                if (isGoodAnchorRef(candidate, forbidden))
                    return candidate;
            }
        }
        return null;
    }

    /**
     * Choose the first good point in the given list
     * @param <T> the type of the candidates
     * @param candidates the point candidates
     * @param converter a function that convert a T to a Point.
     * @param first it true begin at the list start. If false process from the end in the reverse order.
     * @param len the number of points to test in the candidate list.
     * @param forbidden a bad reference point to avoid
     * @return the found good reference point, null if none .
     */
    @objid ("f80050d5-93db-4061-ba1b-1fa44dbed6d9")
    public static <T> Point findGoodAnchorRef(List<T> candidates, Function<T, Point> converter, boolean first, int len, Point forbidden) {
        if (candidates==null)
            return null;
        if (candidates.isEmpty())
            return null;
        
        if (first) {
            int stop = Math.min(len, candidates.size());
            for (int i = 0; i < stop; i++) {
                Point candidate = converter.apply(candidates.get(i));
                if (isGoodAnchorRef(candidate, forbidden))
                    return candidate;
            }
        } else {
            int i = candidates.size() - 1;
            int stop = i - len;
            for (; i > stop && i >= 0; i--) {
                Point candidate = converter.apply(candidates.get(i));
                if (isGoodAnchorRef(candidate, forbidden))
                    return candidate;
            }
        }
        return null;
    }

    /**
     * Choose the first good point in the given list
     * @param <T> the type of the candidates
     * @param candidates the point candidates
     * @param converter a function that convert a T to a Point.
     * @param first it true begin at the list start. If false process from the end in the reverse order.
     * @param len the number of points to test in the candidate list.
     * @param forbidden a bad reference point to avoid
     * @return the found good reference point, null if none .
     */
    @objid ("2697e667-f836-480f-973a-5fee688b67e5")
    public static <T> Point findBoundsIntersection(List<T> candidates, Function<T, Point> converter, boolean first, int len, Rectangle forbidden) {
        if (candidates==null)
            return null;
        if (candidates.isEmpty())
            return null;
        
        if (first) {
            Point p1 = converter.apply(candidates.get(0));
            if (isGoodAnchorRef(p1, forbidden))
                return p1;
            if (candidates.size()==1)
                return null;
            Point p2 = converter.apply(candidates.get(1));
            int i=2;
            int stop = Math.min(len, candidates.size());
            boolean p2IsGood = isGoodAnchorRef(p2, forbidden);
            while(! p2IsGood && i < stop) {
                i++;
                p1 = p2;
                p2 = converter.apply(candidates.get(i));
                p2IsGood = isGoodAnchorRef(p2, forbidden);
            }
            if (p2IsGood)
                return GeomUtils.getSegmentIntersection(p1, p2, forbidden);
            else
                return null;
        } else {
            int i = candidates.size() - 1;
            int stop = i - len;
            Point p1 = converter.apply(candidates.get(i));
        
            if (isGoodAnchorRef(p1, forbidden))
                return p1;
            if (candidates.size()==1)
                return null;
        
            i--;
            Point p2 = converter.apply(candidates.get(i));
            boolean p2IsGood = isGoodAnchorRef(p2, forbidden);
            while(! isGoodAnchorRef(p2, forbidden) && i > stop) {
                i--;
                p1 = p2;
                p2 = converter.apply(candidates.get(i));
                p2IsGood = isGoodAnchorRef(p2, forbidden);
            }
            if (p2IsGood)
                return GeomUtils.getSegmentIntersection(p1, p2, forbidden);
            else
                return null;
        }
        
    }

    /**
     * Tells whether the point is a good anchor reference point.
     * <p>
     * A good anchor reference point is {@link Point} that is:<ul>
     * <li> non null
     * <li> non zero (0, 0)
     * <li> not a forbidden point, usually the center of a figure.
     * </ul>
     * @param candidate a point
     * @param forbidden a forbidden point
     * @return true if the point is a good anchor reference point.
     */
    @objid ("4f804463-ca3e-47e9-9d58-a7eb90f9c2e4")
    public static boolean isGoodAnchorRef(Point candidate, Point forbidden) {
        return candidate != null
                && !candidate.equals(0, 0)
                && !Objects.equals(candidate, forbidden) ;
        
    }

    @objid ("4e568e70-650b-42e2-836d-6b8ba496bccd")
    private static boolean isGoodAnchorRef(Point candidate, Rectangle forbidden) {
        return candidate != null
                && !candidate.equals(0, 0)
                && !forbidden.contains(candidate) ;
        
    }

}
