
package mage.cards.v;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.triggers.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.SacrificeSourceUnlessPaysEffect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;

/**
 *
 * @author LoneFox
 */
public final class VaevictisAsmadi extends CardImpl {

    public VaevictisAsmadi(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{2}{B}{B}{R}{R}{G}{G}");
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.ELDER);
        this.subtype.add(SubType.DRAGON);
        this.power = new MageInt(7);
        this.toughness = new MageInt(7);

        // Flying
        this.addAbility(FlyingAbility.getInstance());
        // At the beginning of your upkeep, sacrifice Vaevictis Asmadi unless you pay {B}{R}{G}.
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(new SacrificeSourceUnlessPaysEffect(new ManaCostsImpl<>("{B}{R}{G}"))));
        // {B}: Vaevictis Asmadi gets +1/+0 until end of turn.
        this.addAbility(new SimpleActivatedAbility(new BoostSourceEffect(1, 0, Duration.EndOfTurn), new ManaCostsImpl<>("{B}")));
        // {R}: Vaevictis Asmadi gets +1/+0 until end of turn.
        this.addAbility(new SimpleActivatedAbility(new BoostSourceEffect(1, 0, Duration.EndOfTurn), new ManaCostsImpl<>("{R}")));
        // {G}: Vaevictis Asmadi gets +1/+0 until end of turn.
        this.addAbility(new SimpleActivatedAbility(new BoostSourceEffect(1, 0, Duration.EndOfTurn), new ManaCostsImpl<>("{G}")));
    }

    private VaevictisAsmadi(final VaevictisAsmadi card) {
        super(card);
    }

    @Override
    public VaevictisAsmadi copy() {
        return new VaevictisAsmadi(this);
    }
}
