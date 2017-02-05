package com.mindtree.pa.forms;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.mindtree.pa.data.ProblemDAO;
import com.mindtree.pa.data.SolutionDAO;
import com.mindtree.pa.data.TestCaseDAO;
import com.mindtree.pa.entity.NodeData;
import com.mindtree.pa.entity.Problem;
import com.mindtree.pa.entity.Solution;
import com.mindtree.pa.entity.TestCase;
import com.mindtree.pa.exception.DataAccessException;
import com.mindtree.pa.util.NodeType;

public class ExplorerPanel extends JPanel {
	NodeData lastSelected;

	private List<Problem> problems;

	private JTree tree;

	private DetailsPanel detailsPanel;

	private ProblemPanel problemPanel;

	private MainForm mainForm;

	private SolutionPanel solutionPanel;

	private TestPanel testPanel;

	JMenuBar mainMenuBar;

	JMenu fileMenu;

	JMenu toolsMenu;

	JMenuItem importMenuItem;

	JMenuItem exitMenuItem;

	JPopupMenu solutionPopupMenu;

	JPopupMenu problemPopupMenu;

	private JMenuItem solutionMenuItem;

	private JMenuItem testcaseMenuItem;

	public ExplorerPanel(List<Problem> problems, DetailsPanel detailsPanel,
			MainForm mainForm) {
		this.problems = problems;
		this.detailsPanel = detailsPanel;
		this.problemPanel = new ProblemPanel();
		this.solutionPanel = new SolutionPanel();
		this.testPanel = new TestPanel();
		lastSelected = null;
		this.mainForm = mainForm;
		setProperties();
		addComponents();
		addListeners();
	}

	public ExplorerPanel(List<Problem> problems) {
		this.problems = problems;
		this.problemPanel = new ProblemPanel();
		this.solutionPanel = new SolutionPanel();
		this.testPanel = new TestPanel();
		lastSelected = null;
		setProperties();
		addComponents();
		addListeners();
	}

	private void setProperties() {
		this.setLayout(new GridLayout(1, 1));
		this.setSize(400, 600);
		this.setBorder(BorderFactory.createEtchedBorder());
	}

	private void addComponents() {
		NodeData rootData = new NodeData(0, 0, NodeType.root, "Problems",
				new ImageIcon("icons/folder.gif"));
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
		rootNode.setUserObject(rootData);
		this.tree = new JTree(rootNode);
		this.tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.tree.putClientProperty("JTree.lineStyle", "Angled");
		this.tree.setFont(new Font("Arial", Font.PLAIN, 12));
		this.tree.setCellRenderer(new NodeRenderer());
		this.tree.expandPath(new TreePath(rootNode.getPath()));
		this.tree.setToolTipText("list of problems");
		this.add(this.tree);

		for (Iterator<Problem> pIter = this.problems.iterator(); pIter
				.hasNext();) {
			Problem problem = (Problem) pIter.next();
			NodeData problemData = new NodeData(problem.getPid(), 0,
					NodeType.problem, problem.getName(), new ImageIcon(
							"icons/problem.gif"));
			DefaultMutableTreeNode problemNode = new DefaultMutableTreeNode(
					problemData);

			problemPopupMenu = new JPopupMenu();
			problemPopupMenu.removeAll();
			JMenuItem item = new JMenuItem("Delete");
			item.addActionListener(this.solutionPanel);
			item.setActionCommand("Delete");
			problemPopupMenu.add(item);

			Solution solution = null;
			try {
				solution = new SolutionDAO().retrieveById(problem.getPid());
			} catch (DataAccessException e) {
				e.printStackTrace();
			}

			NodeData solutionData = new NodeData(problem.getPid(), 0,
					NodeType.solution, "Solution", new ImageIcon(
							"icons/solution.gif"));
			DefaultMutableTreeNode solutionNode = new DefaultMutableTreeNode(
					solutionData);
			problemNode.add(solutionNode);

			solutionPopupMenu = new JPopupMenu();
			solutionPopupMenu.removeAll();
			JMenuItem item1 = new JMenuItem("Execute");
			item1.addActionListener(this.solutionPanel);
			item1.setActionCommand("Execute");
			solutionPopupMenu.add(item1);
			JMenuItem item2 = new JMenuItem("Submit");
			item1.addActionListener(this.solutionPanel);
			item1.setActionCommand("Submit");
			solutionPopupMenu.add(item2);
			JMenuItem item3 = new JMenuItem("Save");
			item1.addActionListener(this.solutionPanel);
			item1.setActionCommand("Save");
			solutionPopupMenu.add(item3);

			for (Iterator<TestCase> tIter = problem.getTestCases().iterator(); tIter
					.hasNext();) {
				TestCase testCase = (TestCase) tIter.next();
				Icon testIcon = null;
				if (testCase.isStatus()) {
					testIcon = new ImageIcon("icons/pass.gif");
				} else {
					testIcon = new ImageIcon("icons/fail.gif");
				}
				NodeData testCaseData = new NodeData(testCase.getPid(),
						testCase.getTid(), NodeType.testCase, testCase
								.getTitle(), testIcon);
				DefaultMutableTreeNode testCaseNode = new DefaultMutableTreeNode(
						testCaseData);
				problemNode.add(testCaseNode);

			}
			rootNode.add(problemNode);
		}

	}

	private void addListeners() {
		this.tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {

				Point p = showNodeDetails(mouseEvent.getX(), mouseEvent.getY());
				if (p == null)
					return;

				int nodeNo = showNodeDetailsInt(mouseEvent.getX(), mouseEvent
						.getY());
				if (mouseEvent.getButton() == 3 && nodeNo == 2) {
					solutionPopupMenu
							.show(tree, (int) p.getX(), (int) p.getY());
				} else if (mouseEvent.getButton() == 3 && nodeNo == 1) {
					problemPopupMenu.show(tree, (int) p.getX(), (int) p.getY());
				}

				else {
					solutionPopupMenu.setVisible(false);
				}

				if (mouseEvent.isPopupTrigger()) {

					solutionPopupMenu.show((JComponent) mouseEvent.getSource(),
							mouseEvent.getX(), mouseEvent.getY());
					solutionPopupMenu.removeAll();

				} else {

					showNodeDetails(mouseEvent.getX(), mouseEvent.getY());
				}
			}
		});
	}

	private Point showNodeDetails(int x, int y) {
		if (this.tree.getPathForLocation(x, y) == null) {
			return null;
		}

		this.tree.setSelectionPath(this.tree.getPathForLocation(x, y));
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) (this.tree
				.getLastSelectedPathComponent());
		NodeData selectedNodeData = (NodeData) selectedNode.getUserObject();
		if (selectedNodeData == lastSelected) {
			this.detailsPanel.setVisible(false);
		}
		lastSelected = selectedNodeData;

		switch (selectedNodeData.getType()) {

		case NodeType.problem:
			Problem problem = null;
			try {
				problem = new ProblemDAO().retrieveById(selectedNodeData
						.getId());
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			this.problemPanel.setProblem(problem, this.mainForm);
			System.out.println("problem id " + selectedNodeData.getId());
			this.detailsPanel.removeAll();
			this.detailsPanel.add(this.problemPanel);
			this.detailsPanel.setVisible(true);

			break;

		case NodeType.solution:
			Solution solution = null;
			try {
				solution = new SolutionDAO().retrieveById(selectedNodeData
						.getId());
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			this.solutionPanel.setSolution(solution, selectedNodeData.getId(),
					this.mainForm);
			System.out.println("solution id" + selectedNodeData.getId());
			this.detailsPanel.removeAll();
			this.detailsPanel.add(this.solutionPanel);
			this.detailsPanel.setVisible(true);
			break;

		case NodeType.testCase:
			TestCase testCase = null;
			try {
				testCase = new TestCaseDAO().retrieveById(selectedNodeData
						.getId(), selectedNodeData.getTid());
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			this.testPanel.setTestCase(testCase);
			this.detailsPanel.removeAll();
			this.detailsPanel.add(this.testPanel);
			this.detailsPanel.setVisible(true);
			break;

		}
		return (new Point(x, y));

	}

	private int showNodeDetailsInt(int x, int y) {
		if (this.tree.getPathForLocation(x, y) == null) {
			return -1;
		}

		this.tree.setSelectionPath(this.tree.getPathForLocation(x, y));
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) (this.tree
				.getLastSelectedPathComponent());
		NodeData selectedNodeData = (NodeData) selectedNode.getUserObject();

		return (selectedNodeData.getType());

	}

}
