<?php

namespace App\Entity;

use App\Repository\HotelRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * @ORM\Entity(repositoryClass=HotelRepository::class)
 */
class Hotel
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups ("post:read")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="veuillez saisir le nom de l'hotel")
     *     @Assert\Length(
     *     min=6,
     *     max=15,
     *     minMessage = "le nom de l'hotel doit etre supérieur a 6 caracteres",
     *     maxMessage  ="le nom de l'hotel ne doit pas depasser 15 caracteres",
     *     allowEmptyString = false
     *     )
     * @Assert\Type(
     * type={"alpha"},
     * message="Nom Hotel  {{ value }} doit contenir seulement des lettres alphabétiques")
     * @Groups ("post:read")
     * @Groups ("post:read")
     */
    private $NomHotel;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="veuillez saisir l'adresse de l'hotel")
     *     @Assert\Length(
     *     min=5,
     *     max=30,
     *     minMessage = "l' Adresse de l'hotel doit etre supérieur a 5 caracteres",
     *     maxMessage  ="l' Adresse de l'hotel ne doit pas depasser 30 caracteres",
     *     allowEmptyString = false
     *     )
     * @Groups ("post:read")
     */
    private $Adresse;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="veuillez saisir le numéro de téléphone")
     *      @Assert\Length(
     *      min=8,
     *      max=8,
     *      minMessage = "Le numéro de téléphone doit etre composé de 8 chiffres",
     *      maxMessage  ="Le numéro de téléphone doit etre composé de 8 chiffres",
     * )
     * @Groups ("post:read")
     */
    private $NumTel;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="veuillez saisir le nombre d'étoiles")
     * @Groups ("post:read")
     */
    private $NbrEtoiles;

    /**
     * @ORM\Column(type="integer")
     * @Groups ("post:read")
     */
    private $NbrChambre;

    /**
     * @ORM\Column(type="integer")
     * @Groups ("post:read")
     * @Groups ("post:read")
     */
    private $CodeH;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="ce champs est obligatoire")
     * @Assert\Email(message = " cette adresse '{{ value }}' est invalide .")
     * @Groups ("post:read")
     */
    private $email;

    /**
     * @ORM\Column(type="string", length=350)
     * @Assert\NotBlank(message="ajouter une image")
     * @Groups ("post:read")
     */

    private $imageHotel;

    /**
     * @ORM\OneToMany(targetEntity=Chambre::class, mappedBy="hotel",cascade={"remove"})
     */
    private $chambre;

    public function __construct()
    {
        $this->chambre = new ArrayCollection();
    }
    

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomHotel(): ?string
    {
        return $this->NomHotel;
    }

    public function setNomHotel(string $NomHotel): self
    {
        $this->NomHotel = $NomHotel;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->Adresse;
    }

    public function setAdresse(string $Adresse): self
    {
        $this->Adresse = $Adresse;

        return $this;
    }

    public function getNumTel(): ?int
    {
        return $this->NumTel;
    }

    public function setNumTel(int $NumTel): self
    {
        $this->NumTel = $NumTel;

        return $this;
    }

    public function getNbrEtoiles(): ?int
    {
        return $this->NbrEtoiles;
    }

    public function setNbrEtoiles(int $NbrEtoiles): self
    {
        $this->NbrEtoiles = $NbrEtoiles;

        return $this;
    }

    public function getNbrChambre(): ?int
    {
        return $this->NbrChambre;
    }

    public function setNbrChambre(int $NbrChambre): self
    {
        $this->NbrChambre = $NbrChambre;

        return $this;
    }

    public function getCodeH(): ?int
    {
        return $this->CodeH;
    }

    public function setCodeH(int $CodeH): self
    {
        $this->CodeH = $CodeH;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getImageHotel()
    {
        return $this->imageHotel;
    }

    public function setImageHotel( $imageHotel)
    {
        $this->imageHotel = $imageHotel;

        return $this;
    }

    /**
     * @return Collection|Chambre[]
     */
    public function getChambre(): Collection
    {
        return $this->chambre;
    }

    public function addChambre(Chambre $chambre): self
    {
        if (!$this->chambre->contains($chambre)) {
            $this->chambre[] = $chambre;
            $chambre->setHotel($this);
        }

        return $this;
    }

    public function removeChambre(Chambre $chambre): self
    {
        if ($this->chambre->removeElement($chambre)) {
            // set the owning side to null (unless already changed)
            if ($chambre->getHotel() === $this) {
                $chambre->setHotel(null);
            }
        }

        return $this;
    }

    public function __toString()
    {
        return(string)$this->getNomHotel();
    }

}