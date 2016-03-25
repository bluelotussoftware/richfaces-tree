/*
 * Copyright (C) 2011-2016  Blue Lotus Software, LLC.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package com.bluelotussoftware.example.jsf.richfaces;

/**
 * Utility class to help with RichFaces Tree components.
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 * @version 1.0
 */
public class TreeUtils {

    /**
     * This method attempts to expand all parent nodes of the provided node in a
     * recursive manner.
     *
     * @param node The starting node.
     * @return the provided node with the parent nodes set as expanded.
     */
    public static TypedTreeNode expandParents(TypedTreeNode node) {

        TypedTreeNode leaf = node;
        if (node != null) {
            while ((node = node.getParent()) != null) {
                node.setExpanded(true);
            }
        }

        return leaf;
    }
}
