package com.mindtree.pa.forms;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.mindtree.pa.entity.NodeData;

public class NodeRenderer extends DefaultTreeCellRenderer {

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		NodeData nodeData = (NodeData) node.getUserObject();
		this.setOpenIcon(nodeData.getIcon());
		this.setClosedIcon(nodeData.getIcon());
		this.setLeafIcon(nodeData.getIcon());
		this.setName(nodeData.getCaption());
		return super.getTreeCellRendererComponent(tree, value, sel, expanded,
				leaf, row, hasFocus);
	}

}
