package powers;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class PowersTree implements TreeSelectionListener
{
    private final JFrame frame = new JFrame();
    private JTree tree;
    private JScrollPane scroll = new JScrollPane();

    private final JTextArea area = new JTextArea();
    private JScrollPane scrollDetails = new JScrollPane();

    public void init()
    {
        frame.setSize(500, 500);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setUpTree();
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(this);
        expandTree(tree);
        setScroll(new JScrollPane(tree));
        scrollDetails = new JScrollPane(area);

        frame.setLayout(new GridLayout(1, 2));
        frame.add(scroll);
        frame.add(scrollDetails);
        frame.setVisible(true);
    }

    private void expandTree(JTree treeToExpand)
    {
        for (int i = 0; i < treeToExpand.getRowCount(); i++)
        {
            treeToExpand.expandRow(i);
        }
    }

    private void setUpTree()
    {
        // root
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Sorcerer Powers");

        // categories
        DefaultMutableTreeNode atWill = new DefaultMutableTreeNode("At-Will");
        DefaultMutableTreeNode encounter = new DefaultMutableTreeNode("Encounter");
        DefaultMutableTreeNode daily = new DefaultMutableTreeNode("Daily");
        DefaultMutableTreeNode utility = new DefaultMutableTreeNode("Utility");

        // at will powers
        DefaultMutableTreeNode chaosBolt = new DefaultMutableTreeNode("Chaos Bolt");
        DefaultMutableTreeNode stormWalk = new DefaultMutableTreeNode("Storm Walk");

        // encounter powers
        DefaultMutableTreeNode shardSwarm = new DefaultMutableTreeNode("Shard Swarm");
        DefaultMutableTreeNode spectralClaw = new DefaultMutableTreeNode("Spectral Claw");

        // daily powers
        DefaultMutableTreeNode placeholder1 = new DefaultMutableTreeNode("Placeholder1");
        DefaultMutableTreeNode placeholder2 = new DefaultMutableTreeNode("Placeholder2");

        // utility powers
        DefaultMutableTreeNode energeticFlight = new DefaultMutableTreeNode("Energetic Flight");
        DefaultMutableTreeNode swiftEscape = new DefaultMutableTreeNode("Swift Escape");

        atWill.add(chaosBolt);
        atWill.add(stormWalk);

        encounter.add(shardSwarm);
        encounter.add(spectralClaw);

        daily.add(placeholder1);
        daily.add(placeholder2);

        utility.add(energeticFlight);
        utility.add(swiftEscape);

        root.add(atWill);
        root.add(encounter);
        root.add(daily);
        root.add(utility);

        tree = new JTree(root);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e)
    {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();

        if (node != null && node.isLeaf())
        {
            switch (node.getUserObject().toString())
            {
            case "Chaos Bolt":
                area.setText(node.getUserObject().toString());
                break;
            case "Storm Walk":
                area.setText(node.getUserObject().toString());
                break;
            case "Shard Swarm":
                area.setText(node.getUserObject().toString());
                break;
            case "Spectral Claw":
                area.setText(node.getUserObject().toString());
                break;
            /**
             * TODO: add cases for dailies
             */
            case "Energetic Flight":
                area.setText(node.getUserObject().toString());
                break;
            case "Swift Escape":
                area.setText(node.getUserObject().toString());
                break;
            }
            frame.revalidate();
            frame.repaint();
        }
    }

    public JScrollPane getScroll()
    {
        return scroll;
    }

    public void setScroll(JScrollPane scroll)
    {
        this.scroll = scroll;
    }
}