package powers;

import java.awt.Dimension;
import java.awt.GridBagLayout;

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
    private static final String CHAOS_BOLT = "Chaos Bolt - At-Will - Arcane, Implement, Psychic\nStandard Action - Ranged 10 - Target: One creature\n\nCharisma vs Will\nHIT: 1d10 + charisma mod psychic damage\n\nIf attack roll was even, perform a secondary attack\n\nSecondary Attack:\n     Target: One creature within 5 squares of the target last hit with this power.\n     Attack: Charisma vs Will\n     HIT: 1d6 psychic damage\n          If you rolled an even roll with the secondary attack roll, repeat the secondary attack.\n          You can attack a creature only once with a single use of this power.";
    private static final String STORM_WALK = "Storm Walk - At-Will - Arcane, Implement, Thunder\nStandard Action - Ranged 10 - Target: One creature\n\nCharisma vs Fortitude\nHIT: 1d8 + charisma mod thunder damage\n\nEffect: Before or after the attack, you shift 1 square.";
    private static final String SHARD_SWARM = "Shard Swarm - Encounter - Teleportation\nMove Action - Close Burst 1 - Target: Each enemy in burst\n\nEffect: Each target grants combat advantage to you until the end of your next turn.\nYou then teleport half your speed.";
    private static final String SPECTRAL_CLAW = "Spectral Claw - Encounter - Arcane, Force, Implement\nStandard Action - Ranged 10 - Target: One creature\n\nCharisma vs Fortitude\nHIT: 1d8 + charisma mod force damage and the target is immobilized until the end of your next turn\n\nIf attack roll was even, you slide the target a number of squares equal to your Dexterity mod.";
    private static final String CHAOS_STORM = "Chaos Storm - Encounter - Arcane, Implement, Lightning, Teleportation\nStandard Action - Area burst 1 within 10 squares - Target: Each creature in burst\n\nCharisma vs Reflex\nHIT: 2d6 + charisma mod lightning damage\n\nEffect: You teleport each target hit by the attack to any other space within the burst.";
    private static final String DAZZLING_RAY = "Dazzling Ray - Daily - Arcane, Implement, Radiant\nStandard Action - Ranged 10 - Target: One creature\n\nCharisma vs Will\nHIT: 6d6 + charisma mod radiant damage\n\nIf attack roll was even, target takes a penalty to attack rolls against you equal to your Dexterity mod (save ends).\n\nMISS: Half damage.";
    private static final String REELING_TORMENT = "Reeling Torment - Daily - Arcane, Charm, Implement, Psychic\nStandard Action - Ranged 10 - Target: One creature\n\nCharisma vs Will\nHIT: 3d8 + charisma mod psychic damage\n\nAt the start of target's turn, you can slide the target 3 squares as a free action (save ends)\n\nMISS: Half damage. At the start of target's turn, you can slide target 1 square as a free action (save ends).";
    private static final String ENERGETIC_FLIGHT = "Energetic Flight - Daily - Arcane\nMinor Action - Personal\n\nEffect: Until the end of your next turn, you gain a fly speed equal to your speed and can hover.";
    private static final String SWIFT_ESCAPE = "Swift Escape - Encounter - Arcane, Teleportation\nImmediate Interrupt - Personal\nTrigger: You are hit by an area or close attack.\n\nEffect: You teleport 2 + Dexterity mod squares.";
    private static final String GHOST_SOUND = "Ghost Sound - At-Will - Arcane, Illusion\nStandard Action - Ranged 10 - Target: One object or unoccupied square\n\nEffect: You cause a sound as quiet as a whisper or as loud as a yelling or fighting creature.\nYou can produce nonvocal sounds as well.";
    private static final String LIGHT = "Light - At-Will - Arcane\nMinor Action - Ranged 5 - Target: One object or unoccupied square\n\nEffect: You cause the target to shed bright light.\nThe light fills the target's square and all squares within 4 squares.\nThe light lasts for 5 minutes. Putting out the light is a free action.\n\nSpecial: You can only have one light cantrip active at a time.";
    private static final String MAGE_HAND = "Mage Hand - At-Will - Arcane, Conjuration\nMinor Action - Ranged 5\n\nEffect: You conjure a spectral, floating hand in an unoccupied square.\nCan handle 20 pounds or less moving it up to 5 squares.\n\nSustain Minor: You can sustain the hand indefinitely.\n\nSpecial: You can create only one hand at a time.";
    private static final String PRESTIDIGITATION = "Prestidigitation - At-Will - Arcane\nStandard Action - Ranged 2\n\nEffect: Use this cantrip to accomplish one of the effects below:\n     - Move up to 1 pound of material\n     - Create a harmless sensory effect, such as a shower of sparks,\n         a puff of wind, faint music, or a strong odor\n     - Color, clean, or soil items in 1 cubic foot for up to 1 hour\n     - Instantly light (or snuff out) a candle, a torch, or a small campfire\n     - Chill, warm, or flavor up to 1 pound of nonliving material for up to 1 hour\n     - Make a small mark or symbol appear on a surface for up to 1 hour\n     - Produce out of nothing a small item or image that\n         exists until the end of your next turn\n     - Make a small, handheld item invisible until the end of your next turn\n\nNothing you create with this cantrip can deal damage, serve as a\nweapon or tool, or hinder another creature's actions\n\nSpecial: You can have as many as three prestidigitation effects active at one time.";
    private static final String MAGIC_MISSILE = "Magic Missile - Encounter - Arcane, Force, Implement\nStandard Action - Ranged 20 - Target: One creature\n\nIntelligence vs Reflex\nHIT: 2d4 + intelligence mod force damage\n\nSpecial: This power counts as a ranged basic attack.";
    private static final String BREW_POTION = "Brew Potion\n\nComponent Cost: Special\nTime: 1 hour\n\nYou create a potion (PHB pg 255) of your level or lower.\nThe ritual's component cost is equal to the price of the potion.";
    private static final String COMPREHEND_LANGUAGE = "Comprehend Language\n\nComponent Cost: 10 gp\nTime: 10 minutes\nDuration: 24 hours\nKey Skill: Arcana\n\nChoose a language you have heard or writing you have seen in the past 24 hours.\nFor language: Understand the language for 24 hours\n     If check is 35 or higher: speak fluently for 24 hours.\nFor writing: Read the language for 24 hours\n     If check is 35 or higher: write for 24 hours.";
    private static final String KNOCK = "Knock\n\nComponent Cost: 35 gp plus 1 healing surge\nTime: 10 minutes\nKey Skill: Arcana\n\nAllows you to open a single locked door, chest, gate, or other object.\nUnlocks Arcane Locked objects.\n\nMake an arcana check with a +5 bonus instead of a thievery check.";
    private static final String SENDING = "Sending\n\nComponent Cost: 50 gp\nTime: 10 minutes\nKey Skill: Arcana\n\nYou convey a mental message of up to 25 words to a person you know.\nThe target can respond likewise.\n- 9 or lower: 10 miles\n- 10-19: 100 miles\n- 20-29: 500 miles\n- 30-39: 1,000 miles\n- 40 or higher: Anywhere on same plane.";
    private static final String WATER_WALK = "Water Walk\n\nComponent Cost: 20 gp\nTime: 10 minutes\nDuration: 1 hour\n\nYou or an ally can move on water as if it were solid ground.\nDoes not prevent moving into water.";

    private final JFrame frame = new JFrame();
    private JTree tree;
    private JScrollPane scroll = new JScrollPane();

    private final JTextArea area = new JTextArea();
    private JScrollPane scrollDetails = new JScrollPane();

    public void init()
    {
        frame.setSize(1000, 650);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setUpTree();
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(this);
        expandTree(tree);

        setScroll(new JScrollPane(tree));
        scroll.setPreferredSize(new Dimension(225, 600));
        scrollDetails = new JScrollPane(area);

        frame.setLayout(new GridBagLayout());
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
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();

        DefaultMutableTreeNode sorcererPowersRoot = new DefaultMutableTreeNode("Sorcerer Powers");

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
        DefaultMutableTreeNode chaosStorm = new DefaultMutableTreeNode("Chaos Storm");

        // daily powers
        DefaultMutableTreeNode placeholder1 = new DefaultMutableTreeNode("Dazzling Ray");
        DefaultMutableTreeNode placeholder2 = new DefaultMutableTreeNode("Reeling Torment");

        // utility powers
        DefaultMutableTreeNode energeticFlight = new DefaultMutableTreeNode("Energetic Flight");
        DefaultMutableTreeNode swiftEscape = new DefaultMutableTreeNode("Swift Escape");

        atWill.add(chaosBolt);
        atWill.add(stormWalk);

        encounter.add(shardSwarm);
        encounter.add(spectralClaw);
        encounter.add(chaosStorm);

        daily.add(placeholder1);
        daily.add(placeholder2);

        utility.add(energeticFlight);
        utility.add(swiftEscape);

        sorcererPowersRoot.add(atWill);
        sorcererPowersRoot.add(encounter);
        sorcererPowersRoot.add(daily);
        sorcererPowersRoot.add(utility);

        DefaultMutableTreeNode wizardPowersRoot = new DefaultMutableTreeNode("Wizard Powers");

        DefaultMutableTreeNode cantrips = new DefaultMutableTreeNode("Cantrips");
        DefaultMutableTreeNode wizardEncounter = new DefaultMutableTreeNode("Encounter");

        DefaultMutableTreeNode ghostSound = new DefaultMutableTreeNode("Ghost Sound");
        DefaultMutableTreeNode light = new DefaultMutableTreeNode("Light");
        DefaultMutableTreeNode mageHand = new DefaultMutableTreeNode("Mage Hand");
        DefaultMutableTreeNode prestidigitation = new DefaultMutableTreeNode("Prestidigitation");

        DefaultMutableTreeNode magicMissile = new DefaultMutableTreeNode("Magic Missile");

        cantrips.add(ghostSound);
        cantrips.add(light);
        cantrips.add(mageHand);
        cantrips.add(prestidigitation);

        wizardEncounter.add(magicMissile);

        wizardPowersRoot.add(cantrips);
        wizardPowersRoot.add(wizardEncounter);

        DefaultMutableTreeNode rituals = new DefaultMutableTreeNode("Rituals");

        DefaultMutableTreeNode brewPotion = new DefaultMutableTreeNode("Brew Potion");
        DefaultMutableTreeNode comprehendLanguage = new DefaultMutableTreeNode("Comprehend Language");
        DefaultMutableTreeNode knock = new DefaultMutableTreeNode("Knock");
        DefaultMutableTreeNode sending = new DefaultMutableTreeNode("Sending");
        DefaultMutableTreeNode waterWalk = new DefaultMutableTreeNode("Water Walk");

        rituals.add(brewPotion);
        rituals.add(comprehendLanguage);
        rituals.add(knock);
        rituals.add(sending);
        rituals.add(waterWalk);

        root.add(sorcererPowersRoot);
        root.add(wizardPowersRoot);
        root.add(rituals);
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
                area.setText(CHAOS_BOLT);
                break;
            case "Storm Walk":
                area.setText(STORM_WALK);
                break;
            case "Shard Swarm":
                area.setText(SHARD_SWARM);
                break;
            case "Spectral Claw":
                area.setText(SPECTRAL_CLAW);
                break;
            case "Chaos Storm":
                area.setText(CHAOS_STORM);
                break;
            case "Dazzling Ray":
                area.setText(DAZZLING_RAY);
                break;
            case "Reeling Torment":
                area.setText(REELING_TORMENT);
                break;
            case "Energetic Flight":
                area.setText(ENERGETIC_FLIGHT);
                break;
            case "Swift Escape":
                area.setText(SWIFT_ESCAPE);
                break;
            case "Magic Missile":
                area.setText(MAGIC_MISSILE);
                break;
            case "Ghost Sound":
                area.setText(GHOST_SOUND);
                break;
            case "Light":
                area.setText(LIGHT);
                break;
            case "Mage Hand":
                area.setText(MAGE_HAND);
                break;
            case "Prestidigitation":
                area.setText(PRESTIDIGITATION);
                break;
            case "Brew Potion":
                area.setText(BREW_POTION);
                break;
            case "Comprehend Language":
                area.setText(COMPREHEND_LANGUAGE);
                break;
            case "Knock":
                area.setText(KNOCK);
                break;
            case "Sending":
                area.setText(SENDING);
                break;
            case "Water Walk":
                area.setText(WATER_WALK);
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