package mage.cards.g;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.dynamicvalue.common.SourcePermanentPowerValue;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.target.common.TargetAnyTarget;

import java.util.UUID;

/**
 *
 * @author LoneFox

 */
public final class GhituFireEater extends CardImpl {

    public GhituFireEater(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{2}{R}");
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.NOMAD);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // {T}, Sacrifice Ghitu Fire-Eater: Ghitu Fire-Eater deals damage equal to its power to any target.
        Ability ability = new SimpleActivatedAbility(new DamageTargetEffect(SourcePermanentPowerValue.NOT_NEGATIVE)
                .setText("it deals damage equal to its power to any target"), new TapSourceCost());
        ability.addCost(new SacrificeSourceCost());
        ability.addTarget(new TargetAnyTarget());
        this.addAbility(ability);
    }

    private GhituFireEater(final GhituFireEater card) {
        super(card);
    }

    @Override
    public GhituFireEater copy() {
        return new GhituFireEater(this);
    }
}
